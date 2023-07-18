import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { CatalogcolorsFormService, CatalogcolorsFormGroup } from './catalogcolors-form.service';
import { ICatalogcolors } from '../catalogcolors.model';
import { CatalogcolorsService } from '../service/catalogcolors.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { ICatalogs } from 'app/entities/catalogs/catalogs.model';
import { CatalogsService } from 'app/entities/catalogs/service/catalogs.service';

@Component({
  standalone: true,
  selector: 'jhi-catalogcolors-update',
  templateUrl: './catalogcolors-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CatalogcolorsUpdateComponent implements OnInit {
  isSaving = false;
  catalogcolors: ICatalogcolors | null = null;

  catalogsSharedCollection: ICatalogs[] = [];

  editForm: CatalogcolorsFormGroup = this.catalogcolorsFormService.createCatalogcolorsFormGroup();

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected catalogcolorsService: CatalogcolorsService,
    protected catalogcolorsFormService: CatalogcolorsFormService,
    protected catalogsService: CatalogsService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareCatalogs = (o1: ICatalogs | null, o2: ICatalogs | null): boolean => this.catalogsService.compareCatalogs(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ catalogcolors }) => {
      this.catalogcolors = catalogcolors;
      if (catalogcolors) {
        this.updateForm(catalogcolors);
      }

      this.loadRelationshipsOptions();
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe({
      error: (err: FileLoadError) =>
        this.eventManager.broadcast(new EventWithContent<AlertError>('tambourBrowserApp.error', { ...err, key: 'error.file.' + err.key })),
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const catalogcolors = this.catalogcolorsFormService.getCatalogcolors(this.editForm);
    if (catalogcolors.id !== null) {
      this.subscribeToSaveResponse(this.catalogcolorsService.update(catalogcolors));
    } else {
      this.subscribeToSaveResponse(this.catalogcolorsService.create(catalogcolors));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICatalogcolors>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(catalogcolors: ICatalogcolors): void {
    this.catalogcolors = catalogcolors;
    this.catalogcolorsFormService.resetForm(this.editForm, catalogcolors);

    this.catalogsSharedCollection = this.catalogsService.addCatalogsToCollectionIfMissing<ICatalogs>(
      this.catalogsSharedCollection,
      catalogcolors.catalog
    );
  }

  protected loadRelationshipsOptions(): void {
    this.catalogsService
      .query()
      .pipe(map((res: HttpResponse<ICatalogs[]>) => res.body ?? []))
      .pipe(
        map((catalogs: ICatalogs[]) =>
          this.catalogsService.addCatalogsToCollectionIfMissing<ICatalogs>(catalogs, this.catalogcolors?.catalog)
        )
      )
      .subscribe((catalogs: ICatalogs[]) => (this.catalogsSharedCollection = catalogs));
  }
}

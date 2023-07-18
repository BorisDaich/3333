import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { CatalogsFormService, CatalogsFormGroup } from './catalogs-form.service';
import { ICatalogs } from '../catalogs.model';
import { CatalogsService } from '../service/catalogs.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';

@Component({
  standalone: true,
  selector: 'jhi-catalogs-update',
  templateUrl: './catalogs-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class CatalogsUpdateComponent implements OnInit {
  isSaving = false;
  catalogs: ICatalogs | null = null;

  editForm: CatalogsFormGroup = this.catalogsFormService.createCatalogsFormGroup();

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected catalogsService: CatalogsService,
    protected catalogsFormService: CatalogsFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ catalogs }) => {
      this.catalogs = catalogs;
      if (catalogs) {
        this.updateForm(catalogs);
      }
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
    const catalogs = this.catalogsFormService.getCatalogs(this.editForm);
    if (catalogs.id !== null) {
      this.subscribeToSaveResponse(this.catalogsService.update(catalogs));
    } else {
      this.subscribeToSaveResponse(this.catalogsService.create(catalogs));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICatalogs>>): void {
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

  protected updateForm(catalogs: ICatalogs): void {
    this.catalogs = catalogs;
    this.catalogsFormService.resetForm(this.editForm, catalogs);
  }
}

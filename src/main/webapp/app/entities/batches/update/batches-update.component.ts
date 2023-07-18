import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { BatchesFormService, BatchesFormGroup } from './batches-form.service';
import { IBatches } from '../batches.model';
import { BatchesService } from '../service/batches.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { ICatalogs } from 'app/entities/catalogs/catalogs.model';
import { CatalogsService } from 'app/entities/catalogs/service/catalogs.service';

@Component({
  standalone: true,
  selector: 'jhi-batches-update',
  templateUrl: './batches-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class BatchesUpdateComponent implements OnInit {
  isSaving = false;
  batches: IBatches | null = null;

  catalogsSharedCollection: ICatalogs[] = [];

  editForm: BatchesFormGroup = this.batchesFormService.createBatchesFormGroup();

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected batchesService: BatchesService,
    protected batchesFormService: BatchesFormService,
    protected catalogsService: CatalogsService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareCatalogs = (o1: ICatalogs | null, o2: ICatalogs | null): boolean => this.catalogsService.compareCatalogs(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ batches }) => {
      this.batches = batches;
      if (batches) {
        this.updateForm(batches);
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
    const batches = this.batchesFormService.getBatches(this.editForm);
    if (batches.id !== null) {
      this.subscribeToSaveResponse(this.batchesService.update(batches));
    } else {
      this.subscribeToSaveResponse(this.batchesService.create(batches));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBatches>>): void {
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

  protected updateForm(batches: IBatches): void {
    this.batches = batches;
    this.batchesFormService.resetForm(this.editForm, batches);

    this.catalogsSharedCollection = this.catalogsService.addCatalogsToCollectionIfMissing<ICatalogs>(
      this.catalogsSharedCollection,
      batches.colorCatalog
    );
  }

  protected loadRelationshipsOptions(): void {
    this.catalogsService
      .query()
      .pipe(map((res: HttpResponse<ICatalogs[]>) => res.body ?? []))
      .pipe(
        map((catalogs: ICatalogs[]) =>
          this.catalogsService.addCatalogsToCollectionIfMissing<ICatalogs>(catalogs, this.batches?.colorCatalog)
        )
      )
      .subscribe((catalogs: ICatalogs[]) => (this.catalogsSharedCollection = catalogs));
  }
}

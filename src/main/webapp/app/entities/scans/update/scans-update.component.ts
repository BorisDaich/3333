import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ScansFormService, ScansFormGroup } from './scans-form.service';
import { IScans } from '../scans.model';
import { ScansService } from '../service/scans.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';
import { IImages } from 'app/entities/images/images.model';
import { ImagesService } from 'app/entities/images/service/images.service';
import { IBatches } from 'app/entities/batches/batches.model';
import { BatchesService } from 'app/entities/batches/service/batches.service';

@Component({
  standalone: true,
  selector: 'jhi-scans-update',
  templateUrl: './scans-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class ScansUpdateComponent implements OnInit {
  isSaving = false;
  scans: IScans | null = null;

  imagesCollection: IImages[] = [];
  batchesSharedCollection: IBatches[] = [];

  editForm: ScansFormGroup = this.scansFormService.createScansFormGroup();

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected scansService: ScansService,
    protected scansFormService: ScansFormService,
    protected imagesService: ImagesService,
    protected batchesService: BatchesService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareImages = (o1: IImages | null, o2: IImages | null): boolean => this.imagesService.compareImages(o1, o2);

  compareBatches = (o1: IBatches | null, o2: IBatches | null): boolean => this.batchesService.compareBatches(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ scans }) => {
      this.scans = scans;
      if (scans) {
        this.updateForm(scans);
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
    const scans = this.scansFormService.getScans(this.editForm);
    if (scans.id !== null) {
      this.subscribeToSaveResponse(this.scansService.update(scans));
    } else {
      this.subscribeToSaveResponse(this.scansService.create(scans));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IScans>>): void {
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

  protected updateForm(scans: IScans): void {
    this.scans = scans;
    this.scansFormService.resetForm(this.editForm, scans);

    this.imagesCollection = this.imagesService.addImagesToCollectionIfMissing<IImages>(this.imagesCollection, scans.image);
    this.batchesSharedCollection = this.batchesService.addBatchesToCollectionIfMissing<IBatches>(
      this.batchesSharedCollection,
      scans.productionBatch
    );
  }

  protected loadRelationshipsOptions(): void {
    this.imagesService
      .query({ 'scansId.specified': 'false' })
      .pipe(map((res: HttpResponse<IImages[]>) => res.body ?? []))
      .pipe(map((images: IImages[]) => this.imagesService.addImagesToCollectionIfMissing<IImages>(images, this.scans?.image)))
      .subscribe((images: IImages[]) => (this.imagesCollection = images));

    this.batchesService
      .query()
      .pipe(map((res: HttpResponse<IBatches[]>) => res.body ?? []))
      .pipe(
        map((batches: IBatches[]) => this.batchesService.addBatchesToCollectionIfMissing<IBatches>(batches, this.scans?.productionBatch))
      )
      .subscribe((batches: IBatches[]) => (this.batchesSharedCollection = batches));
  }
}

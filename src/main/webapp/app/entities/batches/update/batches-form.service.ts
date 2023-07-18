import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IBatches, NewBatches } from '../batches.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IBatches for edit and NewBatchesFormGroupInput for create.
 */
type BatchesFormGroupInput = IBatches | PartialWithRequiredKeyOf<NewBatches>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IBatches | NewBatches> = Omit<
  T,
  'catalogCreatedtime' | 'orderedTime' | 'startedTime' | 'modifiedTime' | 'suspendedTime' | 'finishedTime'
> & {
  catalogCreatedtime?: string | null;
  orderedTime?: string | null;
  startedTime?: string | null;
  modifiedTime?: string | null;
  suspendedTime?: string | null;
  finishedTime?: string | null;
};

type BatchesFormRawValue = FormValueOf<IBatches>;

type NewBatchesFormRawValue = FormValueOf<NewBatches>;

type BatchesFormDefaults = Pick<
  NewBatches,
  'id' | 'catalogIsactive' | 'catalogCreatedtime' | 'orderedTime' | 'startedTime' | 'modifiedTime' | 'suspendedTime' | 'finishedTime'
>;

type BatchesFormGroupContent = {
  id: FormControl<BatchesFormRawValue['id'] | NewBatches['id']>;
  poName: FormControl<BatchesFormRawValue['poName']>;
  sequenceInPo: FormControl<BatchesFormRawValue['sequenceInPo']>;
  scannerId: FormControl<BatchesFormRawValue['scannerId']>;
  previousProductionBatchId: FormControl<BatchesFormRawValue['previousProductionBatchId']>;
  state: FormControl<BatchesFormRawValue['state']>;
  inspectionSequence: FormControl<BatchesFormRawValue['inspectionSequence']>;
  orderedQuantity: FormControl<BatchesFormRawValue['orderedQuantity']>;
  producingQuantity: FormControl<BatchesFormRawValue['producingQuantity']>;
  totalProducingQuantity: FormControl<BatchesFormRawValue['totalProducingQuantity']>;
  remainingQuantity: FormControl<BatchesFormRawValue['remainingQuantity']>;
  totalremainingQuantity: FormControl<BatchesFormRawValue['totalremainingQuantity']>;
  inspectedQuantity: FormControl<BatchesFormRawValue['inspectedQuantity']>;
  totalInspectedQuantity: FormControl<BatchesFormRawValue['totalInspectedQuantity']>;
  failedQuantity: FormControl<BatchesFormRawValue['failedQuantity']>;
  totalFailedQuantity: FormControl<BatchesFormRawValue['totalFailedQuantity']>;
  colorId: FormControl<BatchesFormRawValue['colorId']>;
  colorCode: FormControl<BatchesFormRawValue['colorCode']>;
  colorName: FormControl<BatchesFormRawValue['colorName']>;
  colorBasematerial: FormControl<BatchesFormRawValue['colorBasematerial']>;
  colorLabL: FormControl<BatchesFormRawValue['colorLabL']>;
  colorLabA: FormControl<BatchesFormRawValue['colorLabA']>;
  colorLabB: FormControl<BatchesFormRawValue['colorLabB']>;
  colorA: FormControl<BatchesFormRawValue['colorA']>;
  colorB: FormControl<BatchesFormRawValue['colorB']>;
  colorC: FormControl<BatchesFormRawValue['colorC']>;
  colorD: FormControl<BatchesFormRawValue['colorD']>;
  colorE: FormControl<BatchesFormRawValue['colorE']>;
  colorF: FormControl<BatchesFormRawValue['colorF']>;
  colorG: FormControl<BatchesFormRawValue['colorG']>;
  colorH: FormControl<BatchesFormRawValue['colorH']>;
  colorI: FormControl<BatchesFormRawValue['colorI']>;
  colorJ: FormControl<BatchesFormRawValue['colorJ']>;
  colorK: FormControl<BatchesFormRawValue['colorK']>;
  colorL: FormControl<BatchesFormRawValue['colorL']>;
  colorM: FormControl<BatchesFormRawValue['colorM']>;
  colorN: FormControl<BatchesFormRawValue['colorN']>;
  colorO: FormControl<BatchesFormRawValue['colorO']>;
  colorP: FormControl<BatchesFormRawValue['colorP']>;
  colorQ: FormControl<BatchesFormRawValue['colorQ']>;
  colorR: FormControl<BatchesFormRawValue['colorR']>;
  colorS: FormControl<BatchesFormRawValue['colorS']>;
  colorT: FormControl<BatchesFormRawValue['colorT']>;
  colorU: FormControl<BatchesFormRawValue['colorU']>;
  colorV: FormControl<BatchesFormRawValue['colorV']>;
  colorW: FormControl<BatchesFormRawValue['colorW']>;
  colorX: FormControl<BatchesFormRawValue['colorX']>;
  colorY: FormControl<BatchesFormRawValue['colorY']>;
  colorZ: FormControl<BatchesFormRawValue['colorZ']>;
  catalogId: FormControl<BatchesFormRawValue['catalogId']>;
  catalogExternalid: FormControl<BatchesFormRawValue['catalogExternalid']>;
  catalogName: FormControl<BatchesFormRawValue['catalogName']>;
  catalogVersion: FormControl<BatchesFormRawValue['catalogVersion']>;
  catalogIsactive: FormControl<BatchesFormRawValue['catalogIsactive']>;
  catalogCreatedtime: FormControl<BatchesFormRawValue['catalogCreatedtime']>;
  baseMaterialId: FormControl<BatchesFormRawValue['baseMaterialId']>;
  orderedTime: FormControl<BatchesFormRawValue['orderedTime']>;
  startedTime: FormControl<BatchesFormRawValue['startedTime']>;
  modifiedTime: FormControl<BatchesFormRawValue['modifiedTime']>;
  suspendedTime: FormControl<BatchesFormRawValue['suspendedTime']>;
  finishedTime: FormControl<BatchesFormRawValue['finishedTime']>;
  original: FormControl<BatchesFormRawValue['original']>;
  colorCatalog: FormControl<BatchesFormRawValue['colorCatalog']>;
};

export type BatchesFormGroup = FormGroup<BatchesFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class BatchesFormService {
  createBatchesFormGroup(batches: BatchesFormGroupInput = { id: null }): BatchesFormGroup {
    const batchesRawValue = this.convertBatchesToBatchesRawValue({
      ...this.getFormDefaults(),
      ...batches,
    });
    return new FormGroup<BatchesFormGroupContent>({
      id: new FormControl(
        { value: batchesRawValue.id, disabled: batchesRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required, Validators.maxLength(36)],
        }
      ),
      poName: new FormControl(batchesRawValue.poName, {
        validators: [Validators.required, Validators.maxLength(255)],
      }),
      sequenceInPo: new FormControl(batchesRawValue.sequenceInPo, {
        validators: [Validators.required],
      }),
      scannerId: new FormControl(batchesRawValue.scannerId, {
        validators: [Validators.required],
      }),
      previousProductionBatchId: new FormControl(batchesRawValue.previousProductionBatchId, {
        validators: [Validators.maxLength(36)],
      }),
      state: new FormControl(batchesRawValue.state, {
        validators: [Validators.required, Validators.maxLength(16)],
      }),
      inspectionSequence: new FormControl(batchesRawValue.inspectionSequence, {
        validators: [Validators.required],
      }),
      orderedQuantity: new FormControl(batchesRawValue.orderedQuantity, {
        validators: [Validators.required],
      }),
      producingQuantity: new FormControl(batchesRawValue.producingQuantity, {
        validators: [Validators.required],
      }),
      totalProducingQuantity: new FormControl(batchesRawValue.totalProducingQuantity, {
        validators: [Validators.required],
      }),
      remainingQuantity: new FormControl(batchesRawValue.remainingQuantity, {
        validators: [Validators.required],
      }),
      totalremainingQuantity: new FormControl(batchesRawValue.totalremainingQuantity, {
        validators: [Validators.required],
      }),
      inspectedQuantity: new FormControl(batchesRawValue.inspectedQuantity, {
        validators: [Validators.required],
      }),
      totalInspectedQuantity: new FormControl(batchesRawValue.totalInspectedQuantity, {
        validators: [Validators.required],
      }),
      failedQuantity: new FormControl(batchesRawValue.failedQuantity, {
        validators: [Validators.required],
      }),
      totalFailedQuantity: new FormControl(batchesRawValue.totalFailedQuantity, {
        validators: [Validators.required],
      }),
      colorId: new FormControl(batchesRawValue.colorId, {
        validators: [Validators.maxLength(36)],
      }),
      colorCode: new FormControl(batchesRawValue.colorCode, {
        validators: [Validators.required],
      }),
      colorName: new FormControl(batchesRawValue.colorName, {
        validators: [Validators.required],
      }),
      colorBasematerial: new FormControl(batchesRawValue.colorBasematerial),
      colorLabL: new FormControl(batchesRawValue.colorLabL),
      colorLabA: new FormControl(batchesRawValue.colorLabA),
      colorLabB: new FormControl(batchesRawValue.colorLabB),
      colorA: new FormControl(batchesRawValue.colorA, {
        validators: [Validators.required],
      }),
      colorB: new FormControl(batchesRawValue.colorB, {
        validators: [Validators.required],
      }),
      colorC: new FormControl(batchesRawValue.colorC, {
        validators: [Validators.required],
      }),
      colorD: new FormControl(batchesRawValue.colorD, {
        validators: [Validators.required],
      }),
      colorE: new FormControl(batchesRawValue.colorE, {
        validators: [Validators.required],
      }),
      colorF: new FormControl(batchesRawValue.colorF, {
        validators: [Validators.required],
      }),
      colorG: new FormControl(batchesRawValue.colorG, {
        validators: [Validators.required],
      }),
      colorH: new FormControl(batchesRawValue.colorH, {
        validators: [Validators.required],
      }),
      colorI: new FormControl(batchesRawValue.colorI, {
        validators: [Validators.required],
      }),
      colorJ: new FormControl(batchesRawValue.colorJ, {
        validators: [Validators.required],
      }),
      colorK: new FormControl(batchesRawValue.colorK, {
        validators: [Validators.required],
      }),
      colorL: new FormControl(batchesRawValue.colorL, {
        validators: [Validators.required],
      }),
      colorM: new FormControl(batchesRawValue.colorM, {
        validators: [Validators.required],
      }),
      colorN: new FormControl(batchesRawValue.colorN, {
        validators: [Validators.required],
      }),
      colorO: new FormControl(batchesRawValue.colorO, {
        validators: [Validators.required],
      }),
      colorP: new FormControl(batchesRawValue.colorP, {
        validators: [Validators.required],
      }),
      colorQ: new FormControl(batchesRawValue.colorQ, {
        validators: [Validators.required],
      }),
      colorR: new FormControl(batchesRawValue.colorR, {
        validators: [Validators.required],
      }),
      colorS: new FormControl(batchesRawValue.colorS, {
        validators: [Validators.required],
      }),
      colorT: new FormControl(batchesRawValue.colorT, {
        validators: [Validators.required],
      }),
      colorU: new FormControl(batchesRawValue.colorU, {
        validators: [Validators.required],
      }),
      colorV: new FormControl(batchesRawValue.colorV, {
        validators: [Validators.required],
      }),
      colorW: new FormControl(batchesRawValue.colorW, {
        validators: [Validators.required],
      }),
      colorX: new FormControl(batchesRawValue.colorX, {
        validators: [Validators.required],
      }),
      colorY: new FormControl(batchesRawValue.colorY, {
        validators: [Validators.required],
      }),
      colorZ: new FormControl(batchesRawValue.colorZ, {
        validators: [Validators.required],
      }),
      catalogId: new FormControl(batchesRawValue.catalogId, {
        validators: [Validators.maxLength(36)],
      }),
      catalogExternalid: new FormControl(batchesRawValue.catalogExternalid, {
        validators: [Validators.required],
      }),
      catalogName: new FormControl(batchesRawValue.catalogName, {
        validators: [Validators.required],
      }),
      catalogVersion: new FormControl(batchesRawValue.catalogVersion),
      catalogIsactive: new FormControl(batchesRawValue.catalogIsactive, {
        validators: [Validators.required],
      }),
      catalogCreatedtime: new FormControl(batchesRawValue.catalogCreatedtime),
      baseMaterialId: new FormControl(batchesRawValue.baseMaterialId, {
        validators: [Validators.required],
      }),
      orderedTime: new FormControl(batchesRawValue.orderedTime, {
        validators: [Validators.required],
      }),
      startedTime: new FormControl(batchesRawValue.startedTime),
      modifiedTime: new FormControl(batchesRawValue.modifiedTime),
      suspendedTime: new FormControl(batchesRawValue.suspendedTime),
      finishedTime: new FormControl(batchesRawValue.finishedTime),
      original: new FormControl(batchesRawValue.original),
      colorCatalog: new FormControl(batchesRawValue.colorCatalog),
    });
  }

  getBatches(form: BatchesFormGroup): IBatches | NewBatches {
    return this.convertBatchesRawValueToBatches(form.getRawValue() as BatchesFormRawValue | NewBatchesFormRawValue);
  }

  resetForm(form: BatchesFormGroup, batches: BatchesFormGroupInput): void {
    const batchesRawValue = this.convertBatchesToBatchesRawValue({ ...this.getFormDefaults(), ...batches });
    form.reset(
      {
        ...batchesRawValue,
        id: { value: batchesRawValue.id, disabled: batchesRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): BatchesFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      catalogIsactive: false,
      catalogCreatedtime: currentTime,
      orderedTime: currentTime,
      startedTime: currentTime,
      modifiedTime: currentTime,
      suspendedTime: currentTime,
      finishedTime: currentTime,
    };
  }

  private convertBatchesRawValueToBatches(rawBatches: BatchesFormRawValue | NewBatchesFormRawValue): IBatches | NewBatches {
    return {
      ...rawBatches,
      catalogCreatedtime: dayjs(rawBatches.catalogCreatedtime, DATE_TIME_FORMAT),
      orderedTime: dayjs(rawBatches.orderedTime, DATE_TIME_FORMAT),
      startedTime: dayjs(rawBatches.startedTime, DATE_TIME_FORMAT),
      modifiedTime: dayjs(rawBatches.modifiedTime, DATE_TIME_FORMAT),
      suspendedTime: dayjs(rawBatches.suspendedTime, DATE_TIME_FORMAT),
      finishedTime: dayjs(rawBatches.finishedTime, DATE_TIME_FORMAT),
    };
  }

  private convertBatchesToBatchesRawValue(
    batches: IBatches | (Partial<NewBatches> & BatchesFormDefaults)
  ): BatchesFormRawValue | PartialWithRequiredKeyOf<NewBatchesFormRawValue> {
    return {
      ...batches,
      catalogCreatedtime: batches.catalogCreatedtime ? batches.catalogCreatedtime.format(DATE_TIME_FORMAT) : undefined,
      orderedTime: batches.orderedTime ? batches.orderedTime.format(DATE_TIME_FORMAT) : undefined,
      startedTime: batches.startedTime ? batches.startedTime.format(DATE_TIME_FORMAT) : undefined,
      modifiedTime: batches.modifiedTime ? batches.modifiedTime.format(DATE_TIME_FORMAT) : undefined,
      suspendedTime: batches.suspendedTime ? batches.suspendedTime.format(DATE_TIME_FORMAT) : undefined,
      finishedTime: batches.finishedTime ? batches.finishedTime.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}

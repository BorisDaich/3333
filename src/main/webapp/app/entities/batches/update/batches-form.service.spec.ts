import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../batches.test-samples';

import { BatchesFormService } from './batches-form.service';

describe('Batches Form Service', () => {
  let service: BatchesFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BatchesFormService);
  });

  describe('Service methods', () => {
    describe('createBatchesFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createBatchesFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            poName: expect.any(Object),
            sequenceInPo: expect.any(Object),
            scannerId: expect.any(Object),
            previousProductionBatchId: expect.any(Object),
            state: expect.any(Object),
            inspectionSequence: expect.any(Object),
            orderedQuantity: expect.any(Object),
            producingQuantity: expect.any(Object),
            totalProducingQuantity: expect.any(Object),
            remainingQuantity: expect.any(Object),
            totalremainingQuantity: expect.any(Object),
            inspectedQuantity: expect.any(Object),
            totalInspectedQuantity: expect.any(Object),
            failedQuantity: expect.any(Object),
            totalFailedQuantity: expect.any(Object),
            colorId: expect.any(Object),
            colorCode: expect.any(Object),
            colorName: expect.any(Object),
            colorBasematerial: expect.any(Object),
            colorLabL: expect.any(Object),
            colorLabA: expect.any(Object),
            colorLabB: expect.any(Object),
            colorA: expect.any(Object),
            colorB: expect.any(Object),
            colorC: expect.any(Object),
            colorD: expect.any(Object),
            colorE: expect.any(Object),
            colorF: expect.any(Object),
            colorG: expect.any(Object),
            colorH: expect.any(Object),
            colorI: expect.any(Object),
            colorJ: expect.any(Object),
            colorK: expect.any(Object),
            colorL: expect.any(Object),
            colorM: expect.any(Object),
            colorN: expect.any(Object),
            colorO: expect.any(Object),
            colorP: expect.any(Object),
            colorQ: expect.any(Object),
            colorR: expect.any(Object),
            colorS: expect.any(Object),
            colorT: expect.any(Object),
            colorU: expect.any(Object),
            colorV: expect.any(Object),
            colorW: expect.any(Object),
            colorX: expect.any(Object),
            colorY: expect.any(Object),
            colorZ: expect.any(Object),
            catalogId: expect.any(Object),
            catalogExternalid: expect.any(Object),
            catalogName: expect.any(Object),
            catalogVersion: expect.any(Object),
            catalogIsactive: expect.any(Object),
            catalogCreatedtime: expect.any(Object),
            baseMaterialId: expect.any(Object),
            orderedTime: expect.any(Object),
            startedTime: expect.any(Object),
            modifiedTime: expect.any(Object),
            suspendedTime: expect.any(Object),
            finishedTime: expect.any(Object),
            original: expect.any(Object),
            colorCatalog: expect.any(Object),
          })
        );
      });

      it('passing IBatches should create a new form with FormGroup', () => {
        const formGroup = service.createBatchesFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            poName: expect.any(Object),
            sequenceInPo: expect.any(Object),
            scannerId: expect.any(Object),
            previousProductionBatchId: expect.any(Object),
            state: expect.any(Object),
            inspectionSequence: expect.any(Object),
            orderedQuantity: expect.any(Object),
            producingQuantity: expect.any(Object),
            totalProducingQuantity: expect.any(Object),
            remainingQuantity: expect.any(Object),
            totalremainingQuantity: expect.any(Object),
            inspectedQuantity: expect.any(Object),
            totalInspectedQuantity: expect.any(Object),
            failedQuantity: expect.any(Object),
            totalFailedQuantity: expect.any(Object),
            colorId: expect.any(Object),
            colorCode: expect.any(Object),
            colorName: expect.any(Object),
            colorBasematerial: expect.any(Object),
            colorLabL: expect.any(Object),
            colorLabA: expect.any(Object),
            colorLabB: expect.any(Object),
            colorA: expect.any(Object),
            colorB: expect.any(Object),
            colorC: expect.any(Object),
            colorD: expect.any(Object),
            colorE: expect.any(Object),
            colorF: expect.any(Object),
            colorG: expect.any(Object),
            colorH: expect.any(Object),
            colorI: expect.any(Object),
            colorJ: expect.any(Object),
            colorK: expect.any(Object),
            colorL: expect.any(Object),
            colorM: expect.any(Object),
            colorN: expect.any(Object),
            colorO: expect.any(Object),
            colorP: expect.any(Object),
            colorQ: expect.any(Object),
            colorR: expect.any(Object),
            colorS: expect.any(Object),
            colorT: expect.any(Object),
            colorU: expect.any(Object),
            colorV: expect.any(Object),
            colorW: expect.any(Object),
            colorX: expect.any(Object),
            colorY: expect.any(Object),
            colorZ: expect.any(Object),
            catalogId: expect.any(Object),
            catalogExternalid: expect.any(Object),
            catalogName: expect.any(Object),
            catalogVersion: expect.any(Object),
            catalogIsactive: expect.any(Object),
            catalogCreatedtime: expect.any(Object),
            baseMaterialId: expect.any(Object),
            orderedTime: expect.any(Object),
            startedTime: expect.any(Object),
            modifiedTime: expect.any(Object),
            suspendedTime: expect.any(Object),
            finishedTime: expect.any(Object),
            original: expect.any(Object),
            colorCatalog: expect.any(Object),
          })
        );
      });
    });

    describe('getBatches', () => {
      it('should return NewBatches for default Batches initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createBatchesFormGroup(sampleWithNewData);

        const batches = service.getBatches(formGroup) as any;

        expect(batches).toMatchObject(sampleWithNewData);
      });

      it('should return NewBatches for empty Batches initial value', () => {
        const formGroup = service.createBatchesFormGroup();

        const batches = service.getBatches(formGroup) as any;

        expect(batches).toMatchObject({});
      });

      it('should return IBatches', () => {
        const formGroup = service.createBatchesFormGroup(sampleWithRequiredData);

        const batches = service.getBatches(formGroup) as any;

        expect(batches).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IBatches should not enable id FormControl', () => {
        const formGroup = service.createBatchesFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewBatches should disable id FormControl', () => {
        const formGroup = service.createBatchesFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});

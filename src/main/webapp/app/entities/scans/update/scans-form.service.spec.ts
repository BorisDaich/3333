import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../scans.test-samples';

import { ScansFormService } from './scans-form.service';

describe('Scans Form Service', () => {
  let service: ScansFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ScansFormService);
  });

  describe('Service methods', () => {
    describe('createScansFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createScansFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            scannerId: expect.any(Object),
            sequenceInBatch: expect.any(Object),
            state: expect.any(Object),
            dE: expect.any(Object),
            createdTime: expect.any(Object),
            scannedTime: expect.any(Object),
            inspectedTime: expect.any(Object),
            modifiedTime: expect.any(Object),
            ejectedTime: expect.any(Object),
            image: expect.any(Object),
            productionBatch: expect.any(Object),
          })
        );
      });

      it('passing IScans should create a new form with FormGroup', () => {
        const formGroup = service.createScansFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            scannerId: expect.any(Object),
            sequenceInBatch: expect.any(Object),
            state: expect.any(Object),
            dE: expect.any(Object),
            createdTime: expect.any(Object),
            scannedTime: expect.any(Object),
            inspectedTime: expect.any(Object),
            modifiedTime: expect.any(Object),
            ejectedTime: expect.any(Object),
            image: expect.any(Object),
            productionBatch: expect.any(Object),
          })
        );
      });
    });

    describe('getScans', () => {
      it('should return NewScans for default Scans initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createScansFormGroup(sampleWithNewData);

        const scans = service.getScans(formGroup) as any;

        expect(scans).toMatchObject(sampleWithNewData);
      });

      it('should return NewScans for empty Scans initial value', () => {
        const formGroup = service.createScansFormGroup();

        const scans = service.getScans(formGroup) as any;

        expect(scans).toMatchObject({});
      });

      it('should return IScans', () => {
        const formGroup = service.createScansFormGroup(sampleWithRequiredData);

        const scans = service.getScans(formGroup) as any;

        expect(scans).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IScans should not enable id FormControl', () => {
        const formGroup = service.createScansFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewScans should disable id FormControl', () => {
        const formGroup = service.createScansFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});

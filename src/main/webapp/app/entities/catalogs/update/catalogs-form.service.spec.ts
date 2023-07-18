import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../catalogs.test-samples';

import { CatalogsFormService } from './catalogs-form.service';

describe('Catalogs Form Service', () => {
  let service: CatalogsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CatalogsFormService);
  });

  describe('Service methods', () => {
    describe('createCatalogsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCatalogsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            externalId: expect.any(Object),
            name: expect.any(Object),
            version: expect.any(Object),
            isActive: expect.any(Object),
            createdTime: expect.any(Object),
          })
        );
      });

      it('passing ICatalogs should create a new form with FormGroup', () => {
        const formGroup = service.createCatalogsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            externalId: expect.any(Object),
            name: expect.any(Object),
            version: expect.any(Object),
            isActive: expect.any(Object),
            createdTime: expect.any(Object),
          })
        );
      });
    });

    describe('getCatalogs', () => {
      it('should return NewCatalogs for default Catalogs initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCatalogsFormGroup(sampleWithNewData);

        const catalogs = service.getCatalogs(formGroup) as any;

        expect(catalogs).toMatchObject(sampleWithNewData);
      });

      it('should return NewCatalogs for empty Catalogs initial value', () => {
        const formGroup = service.createCatalogsFormGroup();

        const catalogs = service.getCatalogs(formGroup) as any;

        expect(catalogs).toMatchObject({});
      });

      it('should return ICatalogs', () => {
        const formGroup = service.createCatalogsFormGroup(sampleWithRequiredData);

        const catalogs = service.getCatalogs(formGroup) as any;

        expect(catalogs).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICatalogs should not enable id FormControl', () => {
        const formGroup = service.createCatalogsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCatalogs should disable id FormControl', () => {
        const formGroup = service.createCatalogsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});

import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../catalogcolors.test-samples';

import { CatalogcolorsFormService } from './catalogcolors-form.service';

describe('Catalogcolors Form Service', () => {
  let service: CatalogcolorsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CatalogcolorsFormService);
  });

  describe('Service methods', () => {
    describe('createCatalogcolorsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCatalogcolorsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            name: expect.any(Object),
            baseMaterial: expect.any(Object),
            labL: expect.any(Object),
            labA: expect.any(Object),
            labB: expect.any(Object),
            a: expect.any(Object),
            b: expect.any(Object),
            c: expect.any(Object),
            d: expect.any(Object),
            e: expect.any(Object),
            f: expect.any(Object),
            g: expect.any(Object),
            h: expect.any(Object),
            i: expect.any(Object),
            j: expect.any(Object),
            k: expect.any(Object),
            l: expect.any(Object),
            m: expect.any(Object),
            n: expect.any(Object),
            o: expect.any(Object),
            p: expect.any(Object),
            q: expect.any(Object),
            r: expect.any(Object),
            s: expect.any(Object),
            t: expect.any(Object),
            u: expect.any(Object),
            v: expect.any(Object),
            w: expect.any(Object),
            x: expect.any(Object),
            y: expect.any(Object),
            z: expect.any(Object),
            catalog: expect.any(Object),
          })
        );
      });

      it('passing ICatalogcolors should create a new form with FormGroup', () => {
        const formGroup = service.createCatalogcolorsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            code: expect.any(Object),
            name: expect.any(Object),
            baseMaterial: expect.any(Object),
            labL: expect.any(Object),
            labA: expect.any(Object),
            labB: expect.any(Object),
            a: expect.any(Object),
            b: expect.any(Object),
            c: expect.any(Object),
            d: expect.any(Object),
            e: expect.any(Object),
            f: expect.any(Object),
            g: expect.any(Object),
            h: expect.any(Object),
            i: expect.any(Object),
            j: expect.any(Object),
            k: expect.any(Object),
            l: expect.any(Object),
            m: expect.any(Object),
            n: expect.any(Object),
            o: expect.any(Object),
            p: expect.any(Object),
            q: expect.any(Object),
            r: expect.any(Object),
            s: expect.any(Object),
            t: expect.any(Object),
            u: expect.any(Object),
            v: expect.any(Object),
            w: expect.any(Object),
            x: expect.any(Object),
            y: expect.any(Object),
            z: expect.any(Object),
            catalog: expect.any(Object),
          })
        );
      });
    });

    describe('getCatalogcolors', () => {
      it('should return NewCatalogcolors for default Catalogcolors initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCatalogcolorsFormGroup(sampleWithNewData);

        const catalogcolors = service.getCatalogcolors(formGroup) as any;

        expect(catalogcolors).toMatchObject(sampleWithNewData);
      });

      it('should return NewCatalogcolors for empty Catalogcolors initial value', () => {
        const formGroup = service.createCatalogcolorsFormGroup();

        const catalogcolors = service.getCatalogcolors(formGroup) as any;

        expect(catalogcolors).toMatchObject({});
      });

      it('should return ICatalogcolors', () => {
        const formGroup = service.createCatalogcolorsFormGroup(sampleWithRequiredData);

        const catalogcolors = service.getCatalogcolors(formGroup) as any;

        expect(catalogcolors).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICatalogcolors should not enable id FormControl', () => {
        const formGroup = service.createCatalogcolorsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCatalogcolors should disable id FormControl', () => {
        const formGroup = service.createCatalogcolorsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});

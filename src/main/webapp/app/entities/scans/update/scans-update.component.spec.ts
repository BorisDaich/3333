import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ScansFormService } from './scans-form.service';
import { ScansService } from '../service/scans.service';
import { IScans } from '../scans.model';
import { IImages } from 'app/entities/images/images.model';
import { ImagesService } from 'app/entities/images/service/images.service';
import { IBatches } from 'app/entities/batches/batches.model';
import { BatchesService } from 'app/entities/batches/service/batches.service';

import { ScansUpdateComponent } from './scans-update.component';

describe('Scans Management Update Component', () => {
  let comp: ScansUpdateComponent;
  let fixture: ComponentFixture<ScansUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let scansFormService: ScansFormService;
  let scansService: ScansService;
  let imagesService: ImagesService;
  let batchesService: BatchesService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), ScansUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(ScansUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ScansUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    scansFormService = TestBed.inject(ScansFormService);
    scansService = TestBed.inject(ScansService);
    imagesService = TestBed.inject(ImagesService);
    batchesService = TestBed.inject(BatchesService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call image query and add missing value', () => {
      const scans: IScans = { id: 'CBA' };
      const image: IImages = { id: '8e09a849-4d51-4e78-90ac-a82c781a4e2d' };
      scans.image = image;

      const imageCollection: IImages[] = [{ id: '4c6871e7-ebe3-4d26-bb70-8509d5974506' }];
      jest.spyOn(imagesService, 'query').mockReturnValue(of(new HttpResponse({ body: imageCollection })));
      const expectedCollection: IImages[] = [image, ...imageCollection];
      jest.spyOn(imagesService, 'addImagesToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ scans });
      comp.ngOnInit();

      expect(imagesService.query).toHaveBeenCalled();
      expect(imagesService.addImagesToCollectionIfMissing).toHaveBeenCalledWith(imageCollection, image);
      expect(comp.imagesCollection).toEqual(expectedCollection);
    });

    it('Should call Batches query and add missing value', () => {
      const scans: IScans = { id: 'CBA' };
      const productionBatch: IBatches = { id: '8ce508a7-855b-404c-ba33-9bd6635cbeae' };
      scans.productionBatch = productionBatch;

      const batchesCollection: IBatches[] = [{ id: 'e28d7313-f93e-4d9d-92c6-fe0862c42a02' }];
      jest.spyOn(batchesService, 'query').mockReturnValue(of(new HttpResponse({ body: batchesCollection })));
      const additionalBatches = [productionBatch];
      const expectedCollection: IBatches[] = [...additionalBatches, ...batchesCollection];
      jest.spyOn(batchesService, 'addBatchesToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ scans });
      comp.ngOnInit();

      expect(batchesService.query).toHaveBeenCalled();
      expect(batchesService.addBatchesToCollectionIfMissing).toHaveBeenCalledWith(
        batchesCollection,
        ...additionalBatches.map(expect.objectContaining)
      );
      expect(comp.batchesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const scans: IScans = { id: 'CBA' };
      const image: IImages = { id: '630c868b-2ba0-47f2-9838-b57cb8e33803' };
      scans.image = image;
      const productionBatch: IBatches = { id: 'a8c0dbd7-64a2-4db0-954e-d6bf8a89d7e6' };
      scans.productionBatch = productionBatch;

      activatedRoute.data = of({ scans });
      comp.ngOnInit();

      expect(comp.imagesCollection).toContain(image);
      expect(comp.batchesSharedCollection).toContain(productionBatch);
      expect(comp.scans).toEqual(scans);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IScans>>();
      const scans = { id: 'ABC' };
      jest.spyOn(scansFormService, 'getScans').mockReturnValue(scans);
      jest.spyOn(scansService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ scans });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: scans }));
      saveSubject.complete();

      // THEN
      expect(scansFormService.getScans).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(scansService.update).toHaveBeenCalledWith(expect.objectContaining(scans));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IScans>>();
      const scans = { id: 'ABC' };
      jest.spyOn(scansFormService, 'getScans').mockReturnValue({ id: null });
      jest.spyOn(scansService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ scans: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: scans }));
      saveSubject.complete();

      // THEN
      expect(scansFormService.getScans).toHaveBeenCalled();
      expect(scansService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IScans>>();
      const scans = { id: 'ABC' };
      jest.spyOn(scansService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ scans });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(scansService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareImages', () => {
      it('Should forward to imagesService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(imagesService, 'compareImages');
        comp.compareImages(entity, entity2);
        expect(imagesService.compareImages).toHaveBeenCalledWith(entity, entity2);
      });
    });

    describe('compareBatches', () => {
      it('Should forward to batchesService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(batchesService, 'compareBatches');
        comp.compareBatches(entity, entity2);
        expect(batchesService.compareBatches).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});

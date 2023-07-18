import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { BatchesFormService } from './batches-form.service';
import { BatchesService } from '../service/batches.service';
import { IBatches } from '../batches.model';
import { ICatalogs } from 'app/entities/catalogs/catalogs.model';
import { CatalogsService } from 'app/entities/catalogs/service/catalogs.service';

import { BatchesUpdateComponent } from './batches-update.component';

describe('Batches Management Update Component', () => {
  let comp: BatchesUpdateComponent;
  let fixture: ComponentFixture<BatchesUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let batchesFormService: BatchesFormService;
  let batchesService: BatchesService;
  let catalogsService: CatalogsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), BatchesUpdateComponent],
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
      .overrideTemplate(BatchesUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BatchesUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    batchesFormService = TestBed.inject(BatchesFormService);
    batchesService = TestBed.inject(BatchesService);
    catalogsService = TestBed.inject(CatalogsService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Catalogs query and add missing value', () => {
      const batches: IBatches = { id: 'CBA' };
      const colorCatalog: ICatalogs = { id: 'ddd83b5a-53e1-4a49-831e-d0a04230e59b' };
      batches.colorCatalog = colorCatalog;

      const catalogsCollection: ICatalogs[] = [{ id: '4202de4c-957f-4263-94e0-a74bab72bf28' }];
      jest.spyOn(catalogsService, 'query').mockReturnValue(of(new HttpResponse({ body: catalogsCollection })));
      const additionalCatalogs = [colorCatalog];
      const expectedCollection: ICatalogs[] = [...additionalCatalogs, ...catalogsCollection];
      jest.spyOn(catalogsService, 'addCatalogsToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ batches });
      comp.ngOnInit();

      expect(catalogsService.query).toHaveBeenCalled();
      expect(catalogsService.addCatalogsToCollectionIfMissing).toHaveBeenCalledWith(
        catalogsCollection,
        ...additionalCatalogs.map(expect.objectContaining)
      );
      expect(comp.catalogsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const batches: IBatches = { id: 'CBA' };
      const colorCatalog: ICatalogs = { id: 'ab4a526c-7ab3-48dd-83bd-3178d0043149' };
      batches.colorCatalog = colorCatalog;

      activatedRoute.data = of({ batches });
      comp.ngOnInit();

      expect(comp.catalogsSharedCollection).toContain(colorCatalog);
      expect(comp.batches).toEqual(batches);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBatches>>();
      const batches = { id: 'ABC' };
      jest.spyOn(batchesFormService, 'getBatches').mockReturnValue(batches);
      jest.spyOn(batchesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ batches });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: batches }));
      saveSubject.complete();

      // THEN
      expect(batchesFormService.getBatches).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(batchesService.update).toHaveBeenCalledWith(expect.objectContaining(batches));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBatches>>();
      const batches = { id: 'ABC' };
      jest.spyOn(batchesFormService, 'getBatches').mockReturnValue({ id: null });
      jest.spyOn(batchesService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ batches: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: batches }));
      saveSubject.complete();

      // THEN
      expect(batchesFormService.getBatches).toHaveBeenCalled();
      expect(batchesService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBatches>>();
      const batches = { id: 'ABC' };
      jest.spyOn(batchesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ batches });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(batchesService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareCatalogs', () => {
      it('Should forward to catalogsService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(catalogsService, 'compareCatalogs');
        comp.compareCatalogs(entity, entity2);
        expect(catalogsService.compareCatalogs).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});

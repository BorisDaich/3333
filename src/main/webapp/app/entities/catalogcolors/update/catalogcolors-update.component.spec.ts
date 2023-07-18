import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CatalogcolorsFormService } from './catalogcolors-form.service';
import { CatalogcolorsService } from '../service/catalogcolors.service';
import { ICatalogcolors } from '../catalogcolors.model';
import { ICatalogs } from 'app/entities/catalogs/catalogs.model';
import { CatalogsService } from 'app/entities/catalogs/service/catalogs.service';

import { CatalogcolorsUpdateComponent } from './catalogcolors-update.component';

describe('Catalogcolors Management Update Component', () => {
  let comp: CatalogcolorsUpdateComponent;
  let fixture: ComponentFixture<CatalogcolorsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let catalogcolorsFormService: CatalogcolorsFormService;
  let catalogcolorsService: CatalogcolorsService;
  let catalogsService: CatalogsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), CatalogcolorsUpdateComponent],
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
      .overrideTemplate(CatalogcolorsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CatalogcolorsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    catalogcolorsFormService = TestBed.inject(CatalogcolorsFormService);
    catalogcolorsService = TestBed.inject(CatalogcolorsService);
    catalogsService = TestBed.inject(CatalogsService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Catalogs query and add missing value', () => {
      const catalogcolors: ICatalogcolors = { id: 'CBA' };
      const catalog: ICatalogs = { id: '497ede9c-128a-4617-be5e-2f147aec62e3' };
      catalogcolors.catalog = catalog;

      const catalogsCollection: ICatalogs[] = [{ id: '435584f5-b304-418d-9412-9bb5a997f27d' }];
      jest.spyOn(catalogsService, 'query').mockReturnValue(of(new HttpResponse({ body: catalogsCollection })));
      const additionalCatalogs = [catalog];
      const expectedCollection: ICatalogs[] = [...additionalCatalogs, ...catalogsCollection];
      jest.spyOn(catalogsService, 'addCatalogsToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ catalogcolors });
      comp.ngOnInit();

      expect(catalogsService.query).toHaveBeenCalled();
      expect(catalogsService.addCatalogsToCollectionIfMissing).toHaveBeenCalledWith(
        catalogsCollection,
        ...additionalCatalogs.map(expect.objectContaining)
      );
      expect(comp.catalogsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const catalogcolors: ICatalogcolors = { id: 'CBA' };
      const catalog: ICatalogs = { id: '8fa0e547-b096-47b9-8251-cd7da94d91c8' };
      catalogcolors.catalog = catalog;

      activatedRoute.data = of({ catalogcolors });
      comp.ngOnInit();

      expect(comp.catalogsSharedCollection).toContain(catalog);
      expect(comp.catalogcolors).toEqual(catalogcolors);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICatalogcolors>>();
      const catalogcolors = { id: 'ABC' };
      jest.spyOn(catalogcolorsFormService, 'getCatalogcolors').mockReturnValue(catalogcolors);
      jest.spyOn(catalogcolorsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ catalogcolors });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: catalogcolors }));
      saveSubject.complete();

      // THEN
      expect(catalogcolorsFormService.getCatalogcolors).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(catalogcolorsService.update).toHaveBeenCalledWith(expect.objectContaining(catalogcolors));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICatalogcolors>>();
      const catalogcolors = { id: 'ABC' };
      jest.spyOn(catalogcolorsFormService, 'getCatalogcolors').mockReturnValue({ id: null });
      jest.spyOn(catalogcolorsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ catalogcolors: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: catalogcolors }));
      saveSubject.complete();

      // THEN
      expect(catalogcolorsFormService.getCatalogcolors).toHaveBeenCalled();
      expect(catalogcolorsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICatalogcolors>>();
      const catalogcolors = { id: 'ABC' };
      jest.spyOn(catalogcolorsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ catalogcolors });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(catalogcolorsService.update).toHaveBeenCalled();
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

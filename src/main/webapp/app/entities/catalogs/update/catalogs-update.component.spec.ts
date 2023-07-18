import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CatalogsFormService } from './catalogs-form.service';
import { CatalogsService } from '../service/catalogs.service';
import { ICatalogs } from '../catalogs.model';

import { CatalogsUpdateComponent } from './catalogs-update.component';

describe('Catalogs Management Update Component', () => {
  let comp: CatalogsUpdateComponent;
  let fixture: ComponentFixture<CatalogsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let catalogsFormService: CatalogsFormService;
  let catalogsService: CatalogsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), CatalogsUpdateComponent],
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
      .overrideTemplate(CatalogsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CatalogsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    catalogsFormService = TestBed.inject(CatalogsFormService);
    catalogsService = TestBed.inject(CatalogsService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const catalogs: ICatalogs = { id: 'CBA' };

      activatedRoute.data = of({ catalogs });
      comp.ngOnInit();

      expect(comp.catalogs).toEqual(catalogs);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICatalogs>>();
      const catalogs = { id: 'ABC' };
      jest.spyOn(catalogsFormService, 'getCatalogs').mockReturnValue(catalogs);
      jest.spyOn(catalogsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ catalogs });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: catalogs }));
      saveSubject.complete();

      // THEN
      expect(catalogsFormService.getCatalogs).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(catalogsService.update).toHaveBeenCalledWith(expect.objectContaining(catalogs));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICatalogs>>();
      const catalogs = { id: 'ABC' };
      jest.spyOn(catalogsFormService, 'getCatalogs').mockReturnValue({ id: null });
      jest.spyOn(catalogsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ catalogs: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: catalogs }));
      saveSubject.complete();

      // THEN
      expect(catalogsFormService.getCatalogs).toHaveBeenCalled();
      expect(catalogsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICatalogs>>();
      const catalogs = { id: 'ABC' };
      jest.spyOn(catalogsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ catalogs });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(catalogsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});

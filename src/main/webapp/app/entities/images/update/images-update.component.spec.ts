import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ImagesFormService } from './images-form.service';
import { ImagesService } from '../service/images.service';
import { IImages } from '../images.model';

import { ImagesUpdateComponent } from './images-update.component';

describe('Images Management Update Component', () => {
  let comp: ImagesUpdateComponent;
  let fixture: ComponentFixture<ImagesUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let imagesFormService: ImagesFormService;
  let imagesService: ImagesService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), ImagesUpdateComponent],
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
      .overrideTemplate(ImagesUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ImagesUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    imagesFormService = TestBed.inject(ImagesFormService);
    imagesService = TestBed.inject(ImagesService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const images: IImages = { id: 'CBA' };

      activatedRoute.data = of({ images });
      comp.ngOnInit();

      expect(comp.images).toEqual(images);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IImages>>();
      const images = { id: 'ABC' };
      jest.spyOn(imagesFormService, 'getImages').mockReturnValue(images);
      jest.spyOn(imagesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ images });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: images }));
      saveSubject.complete();

      // THEN
      expect(imagesFormService.getImages).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(imagesService.update).toHaveBeenCalledWith(expect.objectContaining(images));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IImages>>();
      const images = { id: 'ABC' };
      jest.spyOn(imagesFormService, 'getImages').mockReturnValue({ id: null });
      jest.spyOn(imagesService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ images: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: images }));
      saveSubject.complete();

      // THEN
      expect(imagesFormService.getImages).toHaveBeenCalled();
      expect(imagesService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IImages>>();
      const images = { id: 'ABC' };
      jest.spyOn(imagesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ images });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(imagesService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});

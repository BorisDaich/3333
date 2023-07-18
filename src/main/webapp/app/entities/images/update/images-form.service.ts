import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IImages, NewImages } from '../images.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IImages for edit and NewImagesFormGroupInput for create.
 */
type ImagesFormGroupInput = IImages | PartialWithRequiredKeyOf<NewImages>;

type ImagesFormDefaults = Pick<NewImages, 'id'>;

type ImagesFormGroupContent = {
  id: FormControl<IImages['id'] | NewImages['id']>;
  pngContent: FormControl<IImages['pngContent']>;
  pngContentContentType: FormControl<IImages['pngContentContentType']>;
  rawWidth: FormControl<IImages['rawWidth']>;
  rawHeight: FormControl<IImages['rawHeight']>;
  rawFormat: FormControl<IImages['rawFormat']>;
  rawContent: FormControl<IImages['rawContent']>;
  rawContentContentType: FormControl<IImages['rawContentContentType']>;
};

export type ImagesFormGroup = FormGroup<ImagesFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ImagesFormService {
  createImagesFormGroup(images: ImagesFormGroupInput = { id: null }): ImagesFormGroup {
    const imagesRawValue = {
      ...this.getFormDefaults(),
      ...images,
    };
    return new FormGroup<ImagesFormGroupContent>({
      id: new FormControl(
        { value: imagesRawValue.id, disabled: imagesRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required, Validators.maxLength(36)],
        }
      ),
      pngContent: new FormControl(imagesRawValue.pngContent),
      pngContentContentType: new FormControl(imagesRawValue.pngContentContentType),
      rawWidth: new FormControl(imagesRawValue.rawWidth),
      rawHeight: new FormControl(imagesRawValue.rawHeight),
      rawFormat: new FormControl(imagesRawValue.rawFormat),
      rawContent: new FormControl(imagesRawValue.rawContent),
      rawContentContentType: new FormControl(imagesRawValue.rawContentContentType),
    });
  }

  getImages(form: ImagesFormGroup): IImages | NewImages {
    return form.getRawValue() as IImages | NewImages;
  }

  resetForm(form: ImagesFormGroup, images: ImagesFormGroupInput): void {
    const imagesRawValue = { ...this.getFormDefaults(), ...images };
    form.reset(
      {
        ...imagesRawValue,
        id: { value: imagesRawValue.id, disabled: imagesRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ImagesFormDefaults {
    return {
      id: null,
    };
  }
}

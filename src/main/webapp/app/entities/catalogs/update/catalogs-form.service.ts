import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ICatalogs, NewCatalogs } from '../catalogs.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICatalogs for edit and NewCatalogsFormGroupInput for create.
 */
type CatalogsFormGroupInput = ICatalogs | PartialWithRequiredKeyOf<NewCatalogs>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends ICatalogs | NewCatalogs> = Omit<T, 'createdTime'> & {
  createdTime?: string | null;
};

type CatalogsFormRawValue = FormValueOf<ICatalogs>;

type NewCatalogsFormRawValue = FormValueOf<NewCatalogs>;

type CatalogsFormDefaults = Pick<NewCatalogs, 'id' | 'isActive' | 'createdTime'>;

type CatalogsFormGroupContent = {
  id: FormControl<CatalogsFormRawValue['id'] | NewCatalogs['id']>;
  externalId: FormControl<CatalogsFormRawValue['externalId']>;
  name: FormControl<CatalogsFormRawValue['name']>;
  version: FormControl<CatalogsFormRawValue['version']>;
  isActive: FormControl<CatalogsFormRawValue['isActive']>;
  createdTime: FormControl<CatalogsFormRawValue['createdTime']>;
};

export type CatalogsFormGroup = FormGroup<CatalogsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CatalogsFormService {
  createCatalogsFormGroup(catalogs: CatalogsFormGroupInput = { id: null }): CatalogsFormGroup {
    const catalogsRawValue = this.convertCatalogsToCatalogsRawValue({
      ...this.getFormDefaults(),
      ...catalogs,
    });
    return new FormGroup<CatalogsFormGroupContent>({
      id: new FormControl(
        { value: catalogsRawValue.id, disabled: catalogsRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required, Validators.maxLength(36)],
        }
      ),
      externalId: new FormControl(catalogsRawValue.externalId, {
        validators: [Validators.required],
      }),
      name: new FormControl(catalogsRawValue.name, {
        validators: [Validators.required],
      }),
      version: new FormControl(catalogsRawValue.version),
      isActive: new FormControl(catalogsRawValue.isActive, {
        validators: [Validators.required],
      }),
      createdTime: new FormControl(catalogsRawValue.createdTime),
    });
  }

  getCatalogs(form: CatalogsFormGroup): ICatalogs | NewCatalogs {
    return this.convertCatalogsRawValueToCatalogs(form.getRawValue() as CatalogsFormRawValue | NewCatalogsFormRawValue);
  }

  resetForm(form: CatalogsFormGroup, catalogs: CatalogsFormGroupInput): void {
    const catalogsRawValue = this.convertCatalogsToCatalogsRawValue({ ...this.getFormDefaults(), ...catalogs });
    form.reset(
      {
        ...catalogsRawValue,
        id: { value: catalogsRawValue.id, disabled: catalogsRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CatalogsFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      isActive: false,
      createdTime: currentTime,
    };
  }

  private convertCatalogsRawValueToCatalogs(rawCatalogs: CatalogsFormRawValue | NewCatalogsFormRawValue): ICatalogs | NewCatalogs {
    return {
      ...rawCatalogs,
      createdTime: dayjs(rawCatalogs.createdTime, DATE_TIME_FORMAT),
    };
  }

  private convertCatalogsToCatalogsRawValue(
    catalogs: ICatalogs | (Partial<NewCatalogs> & CatalogsFormDefaults)
  ): CatalogsFormRawValue | PartialWithRequiredKeyOf<NewCatalogsFormRawValue> {
    return {
      ...catalogs,
      createdTime: catalogs.createdTime ? catalogs.createdTime.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}

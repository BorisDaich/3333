import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IScans, NewScans } from '../scans.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IScans for edit and NewScansFormGroupInput for create.
 */
type ScansFormGroupInput = IScans | PartialWithRequiredKeyOf<NewScans>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IScans | NewScans> = Omit<
  T,
  'createdTime' | 'scannedTime' | 'inspectedTime' | 'modifiedTime' | 'ejectedTime'
> & {
  createdTime?: string | null;
  scannedTime?: string | null;
  inspectedTime?: string | null;
  modifiedTime?: string | null;
  ejectedTime?: string | null;
};

type ScansFormRawValue = FormValueOf<IScans>;

type NewScansFormRawValue = FormValueOf<NewScans>;

type ScansFormDefaults = Pick<NewScans, 'id' | 'createdTime' | 'scannedTime' | 'inspectedTime' | 'modifiedTime' | 'ejectedTime'>;

type ScansFormGroupContent = {
  id: FormControl<ScansFormRawValue['id'] | NewScans['id']>;
  scannerId: FormControl<ScansFormRawValue['scannerId']>;
  sequenceInBatch: FormControl<ScansFormRawValue['sequenceInBatch']>;
  state: FormControl<ScansFormRawValue['state']>;
  dE: FormControl<ScansFormRawValue['dE']>;
  createdTime: FormControl<ScansFormRawValue['createdTime']>;
  scannedTime: FormControl<ScansFormRawValue['scannedTime']>;
  inspectedTime: FormControl<ScansFormRawValue['inspectedTime']>;
  modifiedTime: FormControl<ScansFormRawValue['modifiedTime']>;
  ejectedTime: FormControl<ScansFormRawValue['ejectedTime']>;
  image: FormControl<ScansFormRawValue['image']>;
  productionBatch: FormControl<ScansFormRawValue['productionBatch']>;
};

export type ScansFormGroup = FormGroup<ScansFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ScansFormService {
  createScansFormGroup(scans: ScansFormGroupInput = { id: null }): ScansFormGroup {
    const scansRawValue = this.convertScansToScansRawValue({
      ...this.getFormDefaults(),
      ...scans,
    });
    return new FormGroup<ScansFormGroupContent>({
      id: new FormControl(
        { value: scansRawValue.id, disabled: scansRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required, Validators.maxLength(36)],
        }
      ),
      scannerId: new FormControl(scansRawValue.scannerId, {
        validators: [Validators.required],
      }),
      sequenceInBatch: new FormControl(scansRawValue.sequenceInBatch, {
        validators: [Validators.required],
      }),
      state: new FormControl(scansRawValue.state, {
        validators: [Validators.required, Validators.maxLength(16)],
      }),
      dE: new FormControl(scansRawValue.dE, {
        validators: [Validators.required],
      }),
      createdTime: new FormControl(scansRawValue.createdTime, {
        validators: [Validators.required],
      }),
      scannedTime: new FormControl(scansRawValue.scannedTime),
      inspectedTime: new FormControl(scansRawValue.inspectedTime),
      modifiedTime: new FormControl(scansRawValue.modifiedTime),
      ejectedTime: new FormControl(scansRawValue.ejectedTime),
      image: new FormControl(scansRawValue.image),
      productionBatch: new FormControl(scansRawValue.productionBatch),
    });
  }

  getScans(form: ScansFormGroup): IScans | NewScans {
    return this.convertScansRawValueToScans(form.getRawValue() as ScansFormRawValue | NewScansFormRawValue);
  }

  resetForm(form: ScansFormGroup, scans: ScansFormGroupInput): void {
    const scansRawValue = this.convertScansToScansRawValue({ ...this.getFormDefaults(), ...scans });
    form.reset(
      {
        ...scansRawValue,
        id: { value: scansRawValue.id, disabled: scansRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ScansFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      createdTime: currentTime,
      scannedTime: currentTime,
      inspectedTime: currentTime,
      modifiedTime: currentTime,
      ejectedTime: currentTime,
    };
  }

  private convertScansRawValueToScans(rawScans: ScansFormRawValue | NewScansFormRawValue): IScans | NewScans {
    return {
      ...rawScans,
      createdTime: dayjs(rawScans.createdTime, DATE_TIME_FORMAT),
      scannedTime: dayjs(rawScans.scannedTime, DATE_TIME_FORMAT),
      inspectedTime: dayjs(rawScans.inspectedTime, DATE_TIME_FORMAT),
      modifiedTime: dayjs(rawScans.modifiedTime, DATE_TIME_FORMAT),
      ejectedTime: dayjs(rawScans.ejectedTime, DATE_TIME_FORMAT),
    };
  }

  private convertScansToScansRawValue(
    scans: IScans | (Partial<NewScans> & ScansFormDefaults)
  ): ScansFormRawValue | PartialWithRequiredKeyOf<NewScansFormRawValue> {
    return {
      ...scans,
      createdTime: scans.createdTime ? scans.createdTime.format(DATE_TIME_FORMAT) : undefined,
      scannedTime: scans.scannedTime ? scans.scannedTime.format(DATE_TIME_FORMAT) : undefined,
      inspectedTime: scans.inspectedTime ? scans.inspectedTime.format(DATE_TIME_FORMAT) : undefined,
      modifiedTime: scans.modifiedTime ? scans.modifiedTime.format(DATE_TIME_FORMAT) : undefined,
      ejectedTime: scans.ejectedTime ? scans.ejectedTime.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}

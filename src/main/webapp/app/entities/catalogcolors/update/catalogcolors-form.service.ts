import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICatalogcolors, NewCatalogcolors } from '../catalogcolors.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICatalogcolors for edit and NewCatalogcolorsFormGroupInput for create.
 */
type CatalogcolorsFormGroupInput = ICatalogcolors | PartialWithRequiredKeyOf<NewCatalogcolors>;

type CatalogcolorsFormDefaults = Pick<NewCatalogcolors, 'id'>;

type CatalogcolorsFormGroupContent = {
  id: FormControl<ICatalogcolors['id'] | NewCatalogcolors['id']>;
  code: FormControl<ICatalogcolors['code']>;
  name: FormControl<ICatalogcolors['name']>;
  baseMaterial: FormControl<ICatalogcolors['baseMaterial']>;
  labL: FormControl<ICatalogcolors['labL']>;
  labA: FormControl<ICatalogcolors['labA']>;
  labB: FormControl<ICatalogcolors['labB']>;
  a: FormControl<ICatalogcolors['a']>;
  b: FormControl<ICatalogcolors['b']>;
  c: FormControl<ICatalogcolors['c']>;
  d: FormControl<ICatalogcolors['d']>;
  e: FormControl<ICatalogcolors['e']>;
  f: FormControl<ICatalogcolors['f']>;
  g: FormControl<ICatalogcolors['g']>;
  h: FormControl<ICatalogcolors['h']>;
  i: FormControl<ICatalogcolors['i']>;
  j: FormControl<ICatalogcolors['j']>;
  k: FormControl<ICatalogcolors['k']>;
  l: FormControl<ICatalogcolors['l']>;
  m: FormControl<ICatalogcolors['m']>;
  n: FormControl<ICatalogcolors['n']>;
  o: FormControl<ICatalogcolors['o']>;
  p: FormControl<ICatalogcolors['p']>;
  q: FormControl<ICatalogcolors['q']>;
  r: FormControl<ICatalogcolors['r']>;
  s: FormControl<ICatalogcolors['s']>;
  t: FormControl<ICatalogcolors['t']>;
  u: FormControl<ICatalogcolors['u']>;
  v: FormControl<ICatalogcolors['v']>;
  w: FormControl<ICatalogcolors['w']>;
  x: FormControl<ICatalogcolors['x']>;
  y: FormControl<ICatalogcolors['y']>;
  z: FormControl<ICatalogcolors['z']>;
  catalog: FormControl<ICatalogcolors['catalog']>;
};

export type CatalogcolorsFormGroup = FormGroup<CatalogcolorsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CatalogcolorsFormService {
  createCatalogcolorsFormGroup(catalogcolors: CatalogcolorsFormGroupInput = { id: null }): CatalogcolorsFormGroup {
    const catalogcolorsRawValue = {
      ...this.getFormDefaults(),
      ...catalogcolors,
    };
    return new FormGroup<CatalogcolorsFormGroupContent>({
      id: new FormControl(
        { value: catalogcolorsRawValue.id, disabled: catalogcolorsRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required, Validators.maxLength(36)],
        }
      ),
      code: new FormControl(catalogcolorsRawValue.code, {
        validators: [Validators.required],
      }),
      name: new FormControl(catalogcolorsRawValue.name, {
        validators: [Validators.required],
      }),
      baseMaterial: new FormControl(catalogcolorsRawValue.baseMaterial),
      labL: new FormControl(catalogcolorsRawValue.labL),
      labA: new FormControl(catalogcolorsRawValue.labA),
      labB: new FormControl(catalogcolorsRawValue.labB),
      a: new FormControl(catalogcolorsRawValue.a, {
        validators: [Validators.required],
      }),
      b: new FormControl(catalogcolorsRawValue.b, {
        validators: [Validators.required],
      }),
      c: new FormControl(catalogcolorsRawValue.c, {
        validators: [Validators.required],
      }),
      d: new FormControl(catalogcolorsRawValue.d, {
        validators: [Validators.required],
      }),
      e: new FormControl(catalogcolorsRawValue.e, {
        validators: [Validators.required],
      }),
      f: new FormControl(catalogcolorsRawValue.f, {
        validators: [Validators.required],
      }),
      g: new FormControl(catalogcolorsRawValue.g, {
        validators: [Validators.required],
      }),
      h: new FormControl(catalogcolorsRawValue.h, {
        validators: [Validators.required],
      }),
      i: new FormControl(catalogcolorsRawValue.i, {
        validators: [Validators.required],
      }),
      j: new FormControl(catalogcolorsRawValue.j, {
        validators: [Validators.required],
      }),
      k: new FormControl(catalogcolorsRawValue.k, {
        validators: [Validators.required],
      }),
      l: new FormControl(catalogcolorsRawValue.l, {
        validators: [Validators.required],
      }),
      m: new FormControl(catalogcolorsRawValue.m, {
        validators: [Validators.required],
      }),
      n: new FormControl(catalogcolorsRawValue.n, {
        validators: [Validators.required],
      }),
      o: new FormControl(catalogcolorsRawValue.o, {
        validators: [Validators.required],
      }),
      p: new FormControl(catalogcolorsRawValue.p, {
        validators: [Validators.required],
      }),
      q: new FormControl(catalogcolorsRawValue.q, {
        validators: [Validators.required],
      }),
      r: new FormControl(catalogcolorsRawValue.r, {
        validators: [Validators.required],
      }),
      s: new FormControl(catalogcolorsRawValue.s, {
        validators: [Validators.required],
      }),
      t: new FormControl(catalogcolorsRawValue.t, {
        validators: [Validators.required],
      }),
      u: new FormControl(catalogcolorsRawValue.u, {
        validators: [Validators.required],
      }),
      v: new FormControl(catalogcolorsRawValue.v, {
        validators: [Validators.required],
      }),
      w: new FormControl(catalogcolorsRawValue.w, {
        validators: [Validators.required],
      }),
      x: new FormControl(catalogcolorsRawValue.x, {
        validators: [Validators.required],
      }),
      y: new FormControl(catalogcolorsRawValue.y, {
        validators: [Validators.required],
      }),
      z: new FormControl(catalogcolorsRawValue.z, {
        validators: [Validators.required],
      }),
      catalog: new FormControl(catalogcolorsRawValue.catalog),
    });
  }

  getCatalogcolors(form: CatalogcolorsFormGroup): ICatalogcolors | NewCatalogcolors {
    return form.getRawValue() as ICatalogcolors | NewCatalogcolors;
  }

  resetForm(form: CatalogcolorsFormGroup, catalogcolors: CatalogcolorsFormGroupInput): void {
    const catalogcolorsRawValue = { ...this.getFormDefaults(), ...catalogcolors };
    form.reset(
      {
        ...catalogcolorsRawValue,
        id: { value: catalogcolorsRawValue.id, disabled: catalogcolorsRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CatalogcolorsFormDefaults {
    return {
      id: null,
    };
  }
}

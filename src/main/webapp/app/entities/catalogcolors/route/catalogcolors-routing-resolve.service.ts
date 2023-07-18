import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICatalogcolors } from '../catalogcolors.model';
import { CatalogcolorsService } from '../service/catalogcolors.service';

export const catalogcolorsResolve = (route: ActivatedRouteSnapshot): Observable<null | ICatalogcolors> => {
  const id = route.params['id'];
  if (id) {
    return inject(CatalogcolorsService)
      .find(id)
      .pipe(
        mergeMap((catalogcolors: HttpResponse<ICatalogcolors>) => {
          if (catalogcolors.body) {
            return of(catalogcolors.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default catalogcolorsResolve;

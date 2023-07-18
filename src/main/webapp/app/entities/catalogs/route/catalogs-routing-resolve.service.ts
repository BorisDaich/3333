import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICatalogs } from '../catalogs.model';
import { CatalogsService } from '../service/catalogs.service';

export const catalogsResolve = (route: ActivatedRouteSnapshot): Observable<null | ICatalogs> => {
  const id = route.params['id'];
  if (id) {
    return inject(CatalogsService)
      .find(id)
      .pipe(
        mergeMap((catalogs: HttpResponse<ICatalogs>) => {
          if (catalogs.body) {
            return of(catalogs.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default catalogsResolve;

import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IScans } from '../scans.model';
import { ScansService } from '../service/scans.service';

export const scansResolve = (route: ActivatedRouteSnapshot): Observable<null | IScans> => {
  const id = route.params['id'];
  if (id) {
    return inject(ScansService)
      .find(id)
      .pipe(
        mergeMap((scans: HttpResponse<IScans>) => {
          if (scans.body) {
            return of(scans.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default scansResolve;

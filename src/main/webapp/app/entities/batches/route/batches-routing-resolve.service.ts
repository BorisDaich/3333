import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IBatches } from '../batches.model';
import { BatchesService } from '../service/batches.service';

export const batchesResolve = (route: ActivatedRouteSnapshot): Observable<null | IBatches> => {
  const id = route.params['id'];
  if (id) {
    return inject(BatchesService)
      .find(id)
      .pipe(
        mergeMap((batches: HttpResponse<IBatches>) => {
          if (batches.body) {
            return of(batches.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        })
      );
  }
  return of(null);
};

export default batchesResolve;

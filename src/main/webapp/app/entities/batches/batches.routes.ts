import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { BatchesComponent } from './list/batches.component';
import { BatchesDetailComponent } from './detail/batches-detail.component';
import { BatchesUpdateComponent } from './update/batches-update.component';
import BatchesResolve from './route/batches-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const batchesRoute: Routes = [
  {
    path: '',
    component: BatchesComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BatchesDetailComponent,
    resolve: {
      batches: BatchesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BatchesUpdateComponent,
    resolve: {
      batches: BatchesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BatchesUpdateComponent,
    resolve: {
      batches: BatchesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default batchesRoute;

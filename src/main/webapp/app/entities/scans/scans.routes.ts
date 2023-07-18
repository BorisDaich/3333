import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ScansComponent } from './list/scans.component';
import { ScansDetailComponent } from './detail/scans-detail.component';
import { ScansUpdateComponent } from './update/scans-update.component';
import ScansResolve from './route/scans-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const scansRoute: Routes = [
  {
    path: '',
    component: ScansComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ScansDetailComponent,
    resolve: {
      scans: ScansResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ScansUpdateComponent,
    resolve: {
      scans: ScansResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ScansUpdateComponent,
    resolve: {
      scans: ScansResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default scansRoute;

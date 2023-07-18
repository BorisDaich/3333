import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CatalogcolorsComponent } from './list/catalogcolors.component';
import { CatalogcolorsDetailComponent } from './detail/catalogcolors-detail.component';
import { CatalogcolorsUpdateComponent } from './update/catalogcolors-update.component';
import CatalogcolorsResolve from './route/catalogcolors-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const catalogcolorsRoute: Routes = [
  {
    path: '',
    component: CatalogcolorsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CatalogcolorsDetailComponent,
    resolve: {
      catalogcolors: CatalogcolorsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CatalogcolorsUpdateComponent,
    resolve: {
      catalogcolors: CatalogcolorsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CatalogcolorsUpdateComponent,
    resolve: {
      catalogcolors: CatalogcolorsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default catalogcolorsRoute;

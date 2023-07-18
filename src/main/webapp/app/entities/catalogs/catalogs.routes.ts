import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CatalogsComponent } from './list/catalogs.component';
import { CatalogsDetailComponent } from './detail/catalogs-detail.component';
import { CatalogsUpdateComponent } from './update/catalogs-update.component';
import CatalogsResolve from './route/catalogs-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const catalogsRoute: Routes = [
  {
    path: '',
    component: CatalogsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CatalogsDetailComponent,
    resolve: {
      catalogs: CatalogsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CatalogsUpdateComponent,
    resolve: {
      catalogs: CatalogsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CatalogsUpdateComponent,
    resolve: {
      catalogs: CatalogsResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default catalogsRoute;

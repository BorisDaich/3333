import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ImagesComponent } from './list/images.component';
import { ImagesDetailComponent } from './detail/images-detail.component';
import { ImagesUpdateComponent } from './update/images-update.component';
import ImagesResolve from './route/images-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const imagesRoute: Routes = [
  {
    path: '',
    component: ImagesComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ImagesDetailComponent,
    resolve: {
      images: ImagesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ImagesUpdateComponent,
    resolve: {
      images: ImagesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ImagesUpdateComponent,
    resolve: {
      images: ImagesResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default imagesRoute;

import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'batches',
        data: { pageTitle: 'tambourBrowserApp.batches.home.title' },
        loadChildren: () => import('./batches/batches.routes'),
      },
      {
        path: 'catalogcolors',
        data: { pageTitle: 'tambourBrowserApp.catalogcolors.home.title' },
        loadChildren: () => import('./catalogcolors/catalogcolors.routes'),
      },
      {
        path: 'catalogs',
        data: { pageTitle: 'tambourBrowserApp.catalogs.home.title' },
        loadChildren: () => import('./catalogs/catalogs.routes'),
      },
      {
        path: 'images',
        data: { pageTitle: 'tambourBrowserApp.images.home.title' },
        loadChildren: () => import('./images/images.routes'),
      },
      {
        path: 'scans',
        data: { pageTitle: 'tambourBrowserApp.scans.home.title' },
        loadChildren: () => import('./scans/scans.routes'),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}

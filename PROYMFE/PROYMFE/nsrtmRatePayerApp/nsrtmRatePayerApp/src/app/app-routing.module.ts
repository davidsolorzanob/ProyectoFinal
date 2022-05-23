import { APP_BASE_HREF } from '@angular/common';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RatepayerDetailComponent } from './core/components/ratepayer-detail/ratepayer-detail.component';
import { RatepayerGridComponent } from './core/components/ratepayer-grid/ratepayer-grid.component';
import { EmptyRouteComponent } from './empty-route/empty-route.component';

const routes: Routes = [
  { path: 'nsrtm-rate-payer-app', component: RatepayerGridComponent },
  { path: 'nsrtm-rate-payer-app/crear', component: RatepayerDetailComponent },
  { path: 'nsrtm-rate-payer-app/obtener/:id', component: RatepayerDetailComponent }, { path: '**', component: EmptyRouteComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [{ provide: APP_BASE_HREF, useValue: '/' }],
})
export class AppRoutingModule { }
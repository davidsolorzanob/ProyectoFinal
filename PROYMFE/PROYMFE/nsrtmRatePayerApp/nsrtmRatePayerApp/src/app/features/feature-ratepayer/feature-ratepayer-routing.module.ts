import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RatepayerDetailComponent } from 'src/app/core/components/ratepayer-detail/ratepayer-detail.component';
import { PageRatepayerComponent } from './pages/page-ratepayer/page-ratepayer.component';

const routes: Routes = [
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FeatureRatepayerRoutingModule { }

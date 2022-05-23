import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FeatureRatepayerRoutingModule } from './feature-ratepayer-routing.module';
import { PageRatepayerComponent } from './pages/page-ratepayer/page-ratepayer.component';
import { RatepayerGridComponent } from 'src/app/core/components/ratepayer-grid/ratepayer-grid.component';
import { RatepayerDetailComponent } from 'src/app/core/components/ratepayer-detail/ratepayer-detail.component';
import { MaterialModule } from 'src/app/material.module';
import { DialogoConfirmacionComponent } from '../../core/components/dialogo-confirmacion/dialogo-confirmacion.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    PageRatepayerComponent,
    RatepayerGridComponent,
    RatepayerDetailComponent,
    DialogoConfirmacionComponent
  ],
  exports: [
    PageRatepayerComponent,
  ],
  imports: [
    CommonModule,
    FeatureRatepayerRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule
  ],
  entryComponents: [
    DialogoConfirmacionComponent
  ]
})
export class FeatureRatepayerModule { }

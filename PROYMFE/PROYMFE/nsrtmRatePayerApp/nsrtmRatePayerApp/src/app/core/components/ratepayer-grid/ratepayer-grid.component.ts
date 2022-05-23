
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';

import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { Contribuyente } from '../../models/contribuyente';
import { ContribuyenteService } from '../../services/contribuyente.service';
import { AlertService } from '@app/services/service.service';
import { ParametroModel } from '@app/models/parametro.model';
import { DialogoConfirmacionComponent } from '../dialogo-confirmacion/dialogo-confirmacion.component';

@Component({
  selector: 'nsrtm-ratepayer-grid',
  templateUrl: './ratepayer-grid.component.html',
  styleUrls: ['./ratepayer-grid.component.scss']
})
export class RatepayerGridComponent implements OnInit {

  grillaFiltrada: any;
  contribuyentes: Contribuyente[] = [];
  parametroModel: ParametroModel = new ParametroModel();

  dataSource: any;

  columnas: string[] = ['contribuyenteId', 'tipoContribuyenteId', 'numeroDocumento', 'apellidoPaterno', 'apellidoMaterno', 'nombres', 'Edit', 'Delete']
  ContribuyenteForm: any

  miFormulario: FormGroup = this.fb.group({
    IdeConParam: [''],
    ApePatParam: [''],
    ApeMatParam: [''],
    NombreParam: [''],
  })

  public page = 0;
  loading = false;
  paginador: any;
  // tslint:disable-next-line: variable-name
  page_size = 5;
  // tslint:disable-next-line: variable-name
  page_number = 1;

  pageSizeOptions = [5, 10, 20, 50, 100];

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator | undefined;

  constructor(private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private contribuyenteService: ContribuyenteService,
    private alertService: AlertService,
    public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.cargaGrilla();
  }

  handlePage(e: PageEvent) {
    this.page_size = e.pageSize;
    this.page_number = e.pageIndex + 1;
  }


  cargaGrilla() {
    console.log("cargaGrilla");
    this.contribuyenteService.fetchContribuyentes().subscribe((res) => {
      this.contribuyentes = res;
      console.log("res", res);
      this.dataSource = new MatTableDataSource<Contribuyente>(this.contribuyentes);
      this.dataSource.paginator = this.paginator;
    });
  }

  buscar() {
    if (this.miFormulario.get('IdeConParam')?.value == '' && this.miFormulario.get('ApePatParam')?.value == '' && this.miFormulario.get('ApeMatParam')?.value == '' && this.miFormulario.get('NombreParam')?.value == '') {
      this.cargaGrilla();
    } else {
      this.parametroModel.codigo = this.miFormulario.get('IdeConParam')?.value;
      this.parametroModel.apellidoPaterno = this.miFormulario.get('ApePatParam')?.value;
      this.parametroModel.apellidoMaterno = this.miFormulario.get('ApeMatParam')?.value;
      this.parametroModel.nombres = this.miFormulario.get('NombreParam')?.value;
      this.miFormulario.get('IdeConParam')?.reset('');
      this.miFormulario.get('ApePatParam')?.reset('');
      this.miFormulario.get('ApeMatParam')?.reset('');
      this.miFormulario.get('NombreParam')?.reset('');
      console.log("PARAMETROS ", this.parametroModel);
      this.contribuyenteService.filter(this.parametroModel).subscribe({
        next: (response) => {
          console.log("response", response);
          this.contribuyentes = response;
          this.dataSource = new MatTableDataSource<Contribuyente>(this.contribuyentes);
          this.dataSource.paginator = this.paginator;
        },
        error: (error) => {
          console.log('Existe un error', error);
        },
        complete: () => {
          console.log('Proceso GetFechaPrgByID completado');
        },
      });
    }

  }

  delete(contribuyente: Contribuyente) {
    const dialog = this.dialog.open(DialogoConfirmacionComponent, {
      width: '350px',
      data: contribuyente
    });
    dialog.afterClosed().subscribe(
      (result) => {
        if (result) {
          this.contribuyenteService.delete(contribuyente.contribuyenteId!).subscribe({
            next: (response) => {
              this.cargaGrilla();
              this.router.navigate(['/nsrtm-rate-payer-app']);
            },
            error: (error) => {
              console.log('had an error');
            },
            complete: () => {
              console.log('Proceso GetFechaPrgByID completado');
            },
          });

        }
      }
    )
  }

}

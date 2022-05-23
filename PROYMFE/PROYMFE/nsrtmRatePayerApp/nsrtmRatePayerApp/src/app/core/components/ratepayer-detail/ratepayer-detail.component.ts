import { MaestroModel } from './../../models/maestro.model';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { ContribuyenteService } from '../../services/contribuyente.service';
import { ContribuyenteModel } from '../../models/contribuyente model';
import { AlertService } from '@app/services/service.service';
import { first } from 'rxjs';
import { MaestroService } from '@app/services/maestro.service';

@Component({
  selector: 'nsrtm-ratepayer-detail',
  templateUrl: './ratepayer-detail.component.html',
  styleUrls: ['./ratepayer-detail.component.scss']
})
export class RatepayerDetailComponent implements OnInit {
  filteredContribuyente: ContribuyenteModel = new ContribuyenteModel();
  public titulo: string = 'Registro Contribuyente';
  public registerForm!: FormGroup;

  maestro: MaestroModel = new MaestroModel();
  isAddMode!: boolean;
  loading = false;
  submitted = false;

  tipoContribuyenteModel: MaestroModel[] = [];
  tipoDocumentoModel: MaestroModel[] = [];

  constructor(private activatedRoute: ActivatedRoute,
    private router: Router,
    private contribuyenteService: ContribuyenteService,
    private maestroService: MaestroService,
    private alertService: AlertService,
    private formBuilder: FormBuilder,
    private snackBar: MatSnackBar) {

  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      contribuyenteId: [''],
      secEjec: [''],
      tipoContribuyenteId: ['', [Validators.required]],
      tipoDocumento: ['', [Validators.required]],
      numeroDocumento: ['', [Validators.required, Validators.pattern('[0-9]{8}')]],
      apellidoPaterno: this.formBuilder.control('', Validators.required),
      apellidoMaterno: ['', [Validators.required]],
      nombres: ['', [Validators.required]],
      razonSocial: [''],
      celular1: ['', [Validators.required, Validators.pattern('[0-9]{9}')]],
      celular2: ['', Validators.pattern('[0-9]{9}')],
      correoElectronico1: ['', [Validators.required, Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$')]],
      correoElectronico2: ['']
    });
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    console.log('id', id);
    if (id == null) {
      this.isAddMode = true;

    } else {
      this.isAddMode = false;
      this.cargarContribuyente(id);
    }

    console.log('REGISTRO FORM', this.registerForm);

  }

  ngAfterViewInit() {
    this.cargaTipoContribuyente();
    this.cargaTipoDocumento();
  }
  cargarContribuyente(id: any) {
    this.contribuyenteService.getContribuyente(id)
      .subscribe({
        next: (res: ContribuyenteModel) => {
          console.log('DATOS CONTRIBUYENTE', res);
          this.registerForm.patchValue(res);
        },
        error: (error) => {
          console.error('Error: ' + error);
        },
        complete: () => {
          console.log('completo la recuperaciónb del Contribuyente');
        }
      });
  }

  onSubmit() {
    this.submitted = true;
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    console.log('tipoContribuyenteId', this.registerForm.get('tipoContribuyenteId'));
    // reset alerts on submit
    this.alertService.clear();

    // stop here if form is invalid
    if (this.registerForm.invalid) {
      console.log('FORMULARIO NO ES VALIDO')
      return;
    }

    this.loading = true;
    console.log('isAddMoode', this.isAddMode)
    if (this.isAddMode) {
      this.createContribuyente();
    } else {
      this.updateContribuyente(id);
    }
  }

  private createContribuyente() {
    this.contribuyenteService.create(this.registerForm.value)
      .pipe(first())
      .subscribe(() => {
        this.mostrarSnakbar('Registro se ha creado satisfactoriamente..!')
        this.router.navigate(['/nsrtm-rate-payer-app'], { relativeTo: this.activatedRoute });
      })
      .add(() => this.loading = false);
  }

  private updateContribuyente(id: any) {
    this.contribuyenteService.update(this.registerForm.value)
      .pipe(first())
      .subscribe(() => {
        this.mostrarSnakbar('Registro se ha actualizado satisfactoriamente..!')
        this.router.navigate(['/nsrtm-rate-payer-app/obtener/' + id]);
      })
      .add(() => this.loading = false);
  }

  mostrarSnakbar(mensaje: string) {

    this.snackBar.open(mensaje, 'ok!', {
      duration: 2500
    });
  }

  cargaTipoContribuyente() {
    this.maestro.tipoMaestroId = 2;
    this.maestroService.get_maestroxtipo(this.maestro)
      .subscribe({
        next: (res: any) => {
          console.log('TIPO DE CONTRIBUYENTE', res);
          this.tipoContribuyenteModel = res;
        },
        error: (error) => {
          console.error('Error: ' + error);
        },
        complete: () => {
          console.log('completo la recuperaciónb del Tipo de Contribuyente');
        }
      });
  }

  cargaTipoDocumento() {
    this.maestro.tipoMaestroId = 1;
    this.maestroService.get_maestroxtipo(this.maestro)
      .subscribe({
        next: (res: any) => {
          console.log('TIPO DE MAESTRO', res);
          this.tipoDocumentoModel = res;
        },
        error: (error) => {
          console.error('Error: ' + error);
        },
        complete: () => {
          console.log('completo la recuperaciónb del Tipo de Documento');
        }
      });
  }
}

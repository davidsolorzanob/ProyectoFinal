import { Injectable } from '@angular/core';
import { Contribuyente } from '../models/contribuyente';
import { of, Observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { map, catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { ContribuyenteModel } from '../models/contribuyente model';
import { ParametroModel } from '@app/models/parametro.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ContribuyenteService {

  // private urlEndPoint: string = environment.url_nsrtm + '/regcontribuyente';
  private urlEndPoint: string = environment.url_nsrtm + '/contribuyente';
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  private _jsonURL = '../../../assets/contibuyente.json';

  constructor(private http: HttpClient, private router: Router
                /*private authService: AuthService*/) { }

  getContribuyentes(): Observable<Contribuyente[]> {

    return this.http.get<Contribuyente[]>(this.urlEndPoint);
  }

  fetchContribuyentes(): Observable<Contribuyente[]> {

    return this.http.get<Contribuyente[]>(`${this.urlEndPoint}/todos`);
  }

  loadobjetos() {
    return this.http
      .get(this._jsonURL)
      .toPromise();
  }

  getContribuyente(id: number): Observable<ContribuyenteModel> {
    let params = new HttpParams().set("id", id);
    return this.http.get<ContribuyenteModel>(`${this.urlEndPoint}/obtener/`, { params });
  }


  create(contribuyente: Contribuyente): Observable<ContribuyenteModel> {
    return this.http.post<ContribuyenteModel>(`${this.urlEndPoint}/crear`, contribuyente);
  }

  update(contribuyente: Contribuyente): Observable<ContribuyenteModel> {
    return this.http.put<ContribuyenteModel>(`${this.urlEndPoint}/editar`, contribuyente);
  }

  delete(id: number): Observable<any> {
    console.log('DELETE SERVICE', id);
    let params = new HttpParams().set("id", id);
    return this.http.delete(`${this.urlEndPoint}/eliminar`, { params });
  }

  filter(parametro: ParametroModel): Observable<Contribuyente[]> {
    return this.http.post<Contribuyente>(`${this.urlEndPoint}/filtrar`, parametro).pipe(
      map((response) => response as Contribuyente[]),
      catchError(e => {
        return throwError(e);
      })
    );
  }
}

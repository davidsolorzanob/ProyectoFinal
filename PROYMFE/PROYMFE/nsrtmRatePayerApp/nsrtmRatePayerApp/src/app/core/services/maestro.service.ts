import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { MaestroModel } from '@app/models/maestro.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MaestroService {

  private urlEndPoint: string = environment.url_nsrtm + '/maestro';

  constructor(
    public http: HttpClient,
    public router: Router,
  ) { }

  get_maestroxtipo(maestro: MaestroModel): Observable<MaestroModel> {
    return this.http.post<MaestroModel>(`${this.urlEndPoint}/filtrar/`, maestro);
  }

}

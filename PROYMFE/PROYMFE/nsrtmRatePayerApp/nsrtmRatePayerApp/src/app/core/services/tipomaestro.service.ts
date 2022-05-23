import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TipomaestroService {

  private urlEndPoint: string = environment.url_nsrtm + '/tipomaestro';

  constructor(
    public http: HttpClient,
    public router: Router,
  ) { }

  get_tipomaestro() {
    return this.http.get<any>(`${this.urlEndPoint}/todos/`);
  }

}

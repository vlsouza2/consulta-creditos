import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConsultaService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  consultar(tipo: 'nfse' | 'credito', numero: string): Observable<any> {
    const endpoint =
      tipo === 'nfse'
        ? `/api/creditos/${numero}`
        : `/api/creditos/credito/${numero}`;
    return this.http.get<any>(`${this.baseUrl}${endpoint}`);
  }
}

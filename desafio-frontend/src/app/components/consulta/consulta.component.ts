import { Component } from '@angular/core';
import { ConsultaService } from 'src/app/services/consulta.service';

@Component({
  selector: 'app-consulta',
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.css']
})
export class ConsultaComponent {
  numero: string = '';
  tipo: 'nfse' | 'credito' = 'nfse';
  resultados: any[] = [];
  erro: string = '';

  constructor(private consultaService: ConsultaService) {}

consultar(): void {
  this.erro = '';
  this.resultados = [];

  if (!this.numero?.toString().trim()) return;

  this.consultaService.consultar(this.tipo, this.numero).subscribe({
    next: data => {
      this.erro = !data ? 'Registro não encontrado.' : '';
      this.resultados = !data ? [] : Array.isArray(data) ? [...data] : [data];
    },
    error: error => {
      this.erro = error.status === 404 
        ? 'Registro não encontrado.' 
        : 'Erro na consulta. Tente novamente.';
      console.error(error);
    },
   
  });
}

}
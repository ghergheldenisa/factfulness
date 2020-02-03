import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Fact} from '../../models/fact.model';
import {Observable} from 'rxjs';
import {absoluteFromSourceFile} from '@angular/compiler-cli/src/ngtsc/file_system';

@Injectable({
  providedIn: 'root'
})
export class FactService {
  baseUrl = 'api/facts';

  constructor(protected http: HttpClient) {
  }

  addFact(fact: Fact): Observable<Fact> {
    return this.http.post<Fact>(`${this.baseUrl}/save`, fact);
  }

  updateFact(fact: Fact): Observable<Fact> {
    return this.http.put<Fact>(`${this.baseUrl}/update`, fact);
  }

  delete(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  getAllForUser() {
    return this.http.get(`${this.baseUrl}/all`);
  }

  get(id: number) {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getRandom() {
    return this.http.get(`${this.baseUrl}/random`);
  }
}

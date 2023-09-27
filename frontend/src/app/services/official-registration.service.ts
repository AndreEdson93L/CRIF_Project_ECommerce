import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/enviroments/enviroments';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class OfficialRegistrationService {
  private registerUrl = environment.registerUrl;
  constructor(private http: HttpClient) { }

  registerAccount(registerUserDTO: User): Observable<any> {
    return this.http.post(this.registerUrl, registerUserDTO, { headers: { 'Content-Type': 'application/json' } });
  }
}

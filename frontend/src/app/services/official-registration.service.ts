import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OfficialRegistrationService {

  private registerUrl = 'http://localhost:8090/api/v1/authentication/register';
  
  constructor(private http: HttpClient) { }

  //POST register micro service 2
  registerAccount(registerUserDTO: any): Observable<any> {

    console.log(registerUserDTO);
    return this.http.post(this.registerUrl, registerUserDTO, { headers: { 'Content-Type': 'application/json' } });
  }  
}

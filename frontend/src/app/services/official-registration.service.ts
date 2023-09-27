import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/enviroments/enviroments';

@Injectable({
  providedIn: 'root'
})
export class OfficialRegistrationService {

  registerUrl = environment.registerUrl;
  
  constructor(private http: HttpClient) { }

  //POST register micro service 2
  registerAccount(registerUserDTO: any): Observable<any> {

    console.log(registerUserDTO);
    return this.http.post(this.registerUrl, registerUserDTO, { headers: { 'Content-Type': 'application/json' } });
  }  
}

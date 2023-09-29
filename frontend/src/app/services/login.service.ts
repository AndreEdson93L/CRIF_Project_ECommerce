import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/enviroments/enviroments';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private url = environment.loginUrl;

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<string> {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(username + ':' + password),
    });

    return this.http.post<string>(this.url, {}, { headers: headers });
  }

  /* isLoggedIn(){
    if(localStorage.getItem('jwtToken') !== null){
      return true
    }
  } */
}

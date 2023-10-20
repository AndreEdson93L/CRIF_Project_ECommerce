import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/enviroments/enviroments';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private urlJwt = environment.loginUrl;
  nickname: Subject<string> = new Subject();


  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<string> {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(username + ':' + password),
    });

    return this.http.post<string>(this.urlJwt, {}, { headers: headers });
  }

  isLoggedIn(text : string){
    this.nickname.next(text)
  }
}

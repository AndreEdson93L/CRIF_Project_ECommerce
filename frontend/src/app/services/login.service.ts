import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/enviroments/enviroments';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private urlJwt = environment.loginUrl;
 private emitChangeSource = new BehaviorSubject<boolean>(false);

 changeEmitted$ = this.emitChangeSource.asObservable()

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<string> {
    const headers = new HttpHeaders({
      Authorization: 'Basic ' + btoa(username + ':' + password),
    });

    return this.http.post<string>(this.urlJwt, {}, { headers: headers });
  }

  isLoggedIn(){
    this.emitChangeSource.next(true)
  }
}

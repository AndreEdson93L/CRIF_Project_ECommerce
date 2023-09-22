import { Injectable } from '@angular/core';
import { Observable, catchError, of } from 'rxjs';
import { User } from '../interfaces/user';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersUrl = 'api/users';  // URL to web api
  
  constructor(private http: HttpClient) { }
  
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl)
      .pipe(
        catchError(this.handleError<User[]>('getUsers', []))
      );
  }

  getUser(id: number): Observable<User>{
    const url = `${this.usersUrl}/${id}`;
    return this.http.get<User>(url);
  }


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
  
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
  
      /* // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`); */
  
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}

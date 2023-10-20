import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject, catchError, of, tap } from 'rxjs';
import { User } from '../models/user';

import { environment, includedUrls } from 'src/enviroments/enviroments';
import { Messageservice } from './messageservice.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  private emitChangeSource = new Subject<boolean>();

  changeEmitted$ = this.emitChangeSource.asObservable()


  constructor(private http: HttpClient, private msgService : Messageservice) { }


  private log(message: string) {
    this.msgService.add(`UserService: ${message}`);
  }
  /**
 * Handle Http operation that failed.
 * So that the app continue running.
 *
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
private handleError<T>(operation = 'operation', result?: T) {
  return (error: any): Observable<T> => {

    // TODO: send the error to remote logging infrastructure
    console.error(error); // log to console instead

    // TODO: better job of transforming error for user consumption
    this.log(`${operation} failed: ${error.message}`);

    // Let the app keep running by returning an empty result.
    return of(result as T);
  };
}

  getUser() : Observable<User> {
    const url = environment.getUserDetail;
    
    let user : any = this.http.get<User>(url)
    
    
    return user
    /* .pipe(
      tap(_ => this.log(`fetched user`)),
      catchError(this.handleError<User>(`getUser`))
    ); */
  }

  emitChangeRole(user: User){
    console.log("ciao userService emitChange");
    
    if(user.role == "ADMIN"){
      this.emitChangeSource.next(true)
    } else{
      this.emitChangeSource.next(false)
    }
    
  }


  deleteUser() : Observable<any> {
    const url = environment.deleteUserDetail;

    let hasBeenDeleted = this.http.delete(url)

    return hasBeenDeleted
  }

  modifyUser(registerUserDTO: User) :Observable<any> {
    const url = environment.updateUserDetail

    let user : any =  this.http.put(url, registerUserDTO, { headers: { 'Content-Type': 'application/json' } });
    return user
  }
}

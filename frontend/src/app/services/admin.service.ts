import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { environment } from 'src/enviroments/enviroments';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  
  constructor(private http : HttpClient) { }

  getUsers() : Observable<User[]> {
    const url = environment.getAllUsersDetails;
    
    let users : any = this.http.get<User[]>(url)
    console.log(users);
    
    return users
    /* .pipe(
      tap(_ => this.log(`fetched user`)),
      catchError(this.handleError<User>(`getUser`))
    ); */
  }
}

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
  }

  deleteUser(email: string) : Observable<any> {
    console.log("Inside delete User Admin method (Service)");
    
    const url = environment.deleteUserAdmin;

    let hasBeenDeleted = this.http.delete(url + '/' + email)
    console.log("End method from the Admin Service");
    console.log(url+'/'+email);
    console.log("Delete? ", hasBeenDeleted.subscribe({
      next: (hasBeenDeleted)=>{
        console.log(typeof hasBeenDeleted);
        console.log("hasBeenDelete: ", hasBeenDeleted);
      },
      error: (hasBeenDeleted)=>{
        console.log("BOH !", hasBeenDeleted);
        
      }
    }))
        
    return hasBeenDeleted
  }
}

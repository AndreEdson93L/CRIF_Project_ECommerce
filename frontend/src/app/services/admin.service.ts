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

  upgradeToAdmin(email : string) {
    const url = environment.promoteUserAdmin;
    console.log(url+"/"+email );
    

    let userResponse : Observable<any>=  this.http.put(url+"/"+email, {});
    console.log("Upgrade? ", userResponse.subscribe({
      next: (userResponse)=>{
        console.log(typeof userResponse);
        console.log("hasBeenUpgraded: ", userResponse);
      },
      error: (userResponse)=>{
        console.log("BOH !", userResponse);
        
      }
    }))
  }



  updateUser(email : string, registerUserDTO: User) {
    const url = environment.updateUserAdmin
    
    let user : any =  this.http.put(url, registerUserDTO, { headers: { 'Content-Type': 'application/json' } });
    return user
  }
  
  
  

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

  getUser(email: string) : Observable<User> {
    const url = environment.getUserAdmin;
    let user : any = this.http.get<User>(url)
    
    
    return user
  }
}

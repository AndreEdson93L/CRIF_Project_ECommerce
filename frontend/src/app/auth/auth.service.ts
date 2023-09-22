import { Injectable } from '@angular/core';
import { UserService } from '../users/user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor( private userService : UserService) { }
  
  isLoggedIn = false
  isAdmin = true

  isAutenticated(){
    return this.isLoggedIn
  }
  isRoleAdmin(){
    return this.isAdmin
  }
  setAuthenticated(){
    this.isLoggedIn = !this.isLoggedIn
    console.log("auth is: "+this.isLoggedIn)
  }
  setRoleAdmin(){
    this.isAdmin = !this.isAdmin
  }
}

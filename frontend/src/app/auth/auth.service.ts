import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isLoggedIn = true
  isAdmin = true

  isAutenticated(){
    return this.isLoggedIn
  }

  isRoleAdmin(){
    return this.isAdmin
  }

  setAuthenticated(){
    if(localStorage.getItem('jwtToken') == null){
       this.isLoggedIn = false
    } else{
      this.isLoggedIn == true
    } 
  }

  setRoleAdmin(){
    if(localStorage.getItem('userRole') == "ADMIN"){
      this.isAdmin = true
    }
    else{
      this.isAdmin = false
    }
  }
  
}

import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isLoggedIn = true
  isAdmin = true

  isAutenticated(){
    console.log("GUARD CONTROL.. inside service guard");
    console.log("IsLoggedId: " + this.isLoggedIn);
    
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

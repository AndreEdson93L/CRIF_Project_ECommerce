import { Component, Input, OnChanges } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent{
  
  @Input() isLoggedIn : boolean = false
  @Input() isAdmin : boolean = false



  
  constructor(private router: Router, private loginService : LoginService, private userService: UserService) {
    loginService.changeEmitted$.subscribe(logInfo =>{
      this.isLoggedIn = logInfo
    })
    userService.changeEmitted$.subscribe(userInfo => {
      let role = userInfo
      console.log(role);
      
      if(role == "ADMIN"){
        this.isAdmin = true
      } else if(role == "USER"){
        this.isAdmin = false
      }
    })
    
  }

  isActive(path: string): boolean {
    return this.router.url === path;
  }

  
  logout(): void{

    console.log("Logout has been called");
    this.loginService.loggedOut();
    
    localStorage.clear();
    console.log("localStorage has been clean up");
    
  }
}

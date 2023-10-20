import { Component, Input} from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

public msg: {} | undefined 
@Input()user: User | undefined;
@Input() isLoggedIn : boolean = false
@Input() isAdmin : boolean = false


  
constructor(private router: Router, private loginService : LoginService, private userService: UserService) {
  loginService.changeEmitted$.subscribe(logInfo =>{
    this.isLoggedIn = logInfo
    
  })
  userService.changeEmitted$.subscribe(roleInfo =>{
    this.isAdmin = roleInfo
  })
 }
  

  isActive(path: string): boolean {
    return this.router.url === path;
  }

  
}

import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
  @Input() user : User | undefined 
  @Input() isLoggedIn : boolean = false
  @Input() userNickname : string | undefined

  constructor(private userService : UserService, private loginService: LoginService){

    loginService.changeEmitted$.subscribe(loginInfo =>{
      this.isLoggedIn = loginInfo
    })
  }
  ngOnInit(): void {
    this.getUser()
  }


  getUser(): void {

    this.userService.getUser().subscribe({
      next: (user) =>{
        
        this.user = user 
        this.userNickname = user.nickname
        this.userService.changeRole(user.role)
      },
      error: (err) => {
        console.log('You Failed!', err);
      },
    });

  }

}

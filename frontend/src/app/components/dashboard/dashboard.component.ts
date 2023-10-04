import { Component } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  public user!: User;
  public isAuthenticated = false
  public userNickname : string | null | undefined
  
  public userRole : string | null | undefined
  constructor(private userService : UserService){}

  ngOnInit(): void {
    this.getUser();
    if(!this.user == null){
      localStorage.setItem('userNickname', this.user.nickname)
      localStorage.setItem('userRole', this.user.role)
    }
  }
  
  getUser(): void {

    this.userService.getUser().subscribe({
      next: (user) =>{
        console.log("User details: ", user)
        this.user = user 
      },
      error: (err) => {
        console.log('You Failed!', err);
      },
    });    
  }


  getUserNickname() : string| undefined{
    return this.user?.nickname;
  }
}

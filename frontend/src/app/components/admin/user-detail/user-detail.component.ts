import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class AdminUserDetailComponent {
  @Input()
  user!: User;
  @Input() hasBeenDeleted: boolean | undefined;



  constructor(
    private userService : UserService,
    private router : Router
  ) {}

  ngOnInit(): void {
    this.getUser();
    if(!this.user == null){
      localStorage.setItem('userNickname', this.user.nickname)
      localStorage.setItem('userRole', this.user.role)
    }
    
    
  }

  onClickModify(){
    this.router.navigate(["/admin/modify-user-details"])
  }
  
  
  onClickDelete() : void{
    this.userService.deleteUser().subscribe({
      next: (hasBeenDeleted) =>{
        this.hasBeenDeleted = hasBeenDeleted
      },
      error: (err) => {
        console.log('You Failed!', err);
      },
    });    
    if(this.hasBeenDeleted){
        this.router.navigate(["/"])
    }
    localStorage.clear()
  }
  
  getUser(): void {

    this.userService.getUser().subscribe({
      next: (user) =>{
        console.log("User details: ", user)
        this.user = user 
        console.log(this.user)
      },
      error: (err) => {
        console.log('You Failed!', err);
      },
    });    
  }
}

import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent {
  @Input()
  user!: User;
  @Input() hasBeenDeleted: boolean= false;



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
    this.router.navigate(["/modify-user-details"])
  }
  
  
  onClickDelete() : void{
    this.userService.deleteUser().subscribe({
      next: (hasBeenDeleted) =>{
        console.log(typeof hasBeenDeleted);
        
        this.hasBeenDeleted = hasBeenDeleted
        console.log("ciao")
        localStorage.clear()
        this.router.navigate(["/"])
      },
      error: (err) => {
        console.log('You Failed!', err);
      },
    });    
    if(this.hasBeenDeleted){
        
    }
    
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

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
  @Input() user!: User;
  @Input() hasBeenDeleted: boolean= false;



  constructor(
    private userService : UserService,
    private router : Router
  ) {}

  ngOnInit(): void {
    this.getUser();
  }

  onClickModify(){
    this.router.navigate(["/modify-user-details"])
  }
  
  
  onClickDelete() : void{
    this.userService.deleteUser().subscribe({
      next: (hasBeenDeleted) =>{
        console.log(typeof hasBeenDeleted);
        
        this.hasBeenDeleted = hasBeenDeleted
        localStorage.clear()
        this.router.navigate(["/home"])
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
        
        this.user = user 
       
      },
      error: (err) => {
        console.log('You Failed!', err);
      },
    });

  }

}

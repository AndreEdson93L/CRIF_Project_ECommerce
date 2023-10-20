import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-modify-user-details',
  templateUrl: './modify-user-details.component.html',
  styleUrls: ['./modify-user-details.component.css']
})
export class ModifyUserDetailsComponent implements OnInit {


  @Input() user!: User;

  modifyForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
     private userService: UserService,
      private router: Router, 
      private loginService : LoginService
      ) { }
  



  ngOnInit(): void {
    

    this.getUserDetails()
    this.modifyForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      nickname: ['', Validators.required],
    });
  }


  getUserDetails(): void {

    this.userService.getUser().subscribe({
      next: (user) =>{
       
        this.user = user 

      },
      error: (err) => {
        console.log('Get User Failed!', err);
      },
    });    
  }



  modifyUserDetails() {
    let hasBeeenSucces = false
    if (this.modifyForm.valid) {
      const user = this.modifyForm.value as User;
      console.log(user);
      
      this.userService.modifyUser(user).subscribe({
        next: (response) => {
          console.log('User modified:', response);
          hasBeeenSucces = true
          if(hasBeeenSucces){
            localStorage.clear()
            //add pop up on succesfull op to notify user
            this.router.navigate(["/login"])
          }
        },
        error: (err) => {
          console.log('Modification failed:', err);
        }
      });
    }
    
    
  }  
}

import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-modify-user-detail',
  templateUrl: './modify-user-detail.component.html',
  styleUrls: ['./modify-user-detail.component.css']
})
export class AdminModifyUserDetailComponent {

  @Input() user!: User;

  adminModifyForm!: FormGroup;

  email = ''

  hasBeeenSucces = false

  constructor(
    private fb: FormBuilder,
     private adminService: AdminService,
      private router: Router, 
      private route : ActivatedRoute
      ) { }
  



  ngOnInit(): void {
    console.log("ok");
    
    
     this.email = this.route.snapshot.params.email;

    //this.getUserDetails(this.email)
    this.adminModifyForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      nickname: ['', Validators.required],
    });
    console.log("fine");
    
  }


  getUserDetails(email : string): void {

    this.adminService.getUser(email).subscribe({
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
    if (this.adminModifyForm.valid) {
      const user = this.adminModifyForm.value as User;
      console.log(user);
      
      this.adminService.updateUser(this.email, user).subscribe({
        next: (response: any) => {
          console.log('User modified:', response);
          hasBeeenSucces = true
        }
          /* if(hasBeeenSucces){
            localStorage.clear()
            //add pop up on succesfull op to notify user
            this.router.navigate(["/login"])
          }
        },
        error: (err: any) => {
          console.log('Modification failed:', err);
        } */
      });
    }
  }
}

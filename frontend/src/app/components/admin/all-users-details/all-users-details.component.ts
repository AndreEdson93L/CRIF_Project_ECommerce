import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-all-users-details',
  templateUrl: './all-users-details.component.html',
  styleUrls: ['./all-users-details.component.css']
})
export class AllUsersDetailsComponent implements OnInit{

  @Input() users : User[]|undefined

  constructor(private adminService: AdminService, private router: Router){}

  ngOnInit(){
    this.getUsers()
  }

  getUsers(): void {

    this.adminService.getUsers().subscribe({
      next: (users) =>{
        console.log("User details: ", users)
        this.users = users
        console.log(this.users)
      },
      error: (err) => {
        console.log('You Failed!', err);
      },
    });  
  }


  deleteUser(email : string) {
    console.log("Trying to delete an user...")
    this.adminService.deleteUser(email)
    this.getUsers() 
    
  }

  updateUserDetails(email : string) {
    let emailEncoded = encodeURIComponent(email)
    this.router.navigateByUrl("admin-modify-user-details/"+emailEncoded)
  }

  upgradeToAdmin(email : string){
    this.adminService.upgradeToAdmin(email)
    this.getUsers()
  }

}

import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-all-users-details',
  templateUrl: './all-users-details.component.html',
  styleUrls: ['./all-users-details.component.css']
})
export class AllUsersDetailsComponent implements OnInit{

  @Input() users : User[]|undefined

  constructor(private adminService: AdminService){}

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

}

import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/interfaces/user';
import { RegisterService } from 'src/app/register/services/register.service';
import { UserService } from 'src/app/users/user.service';


@Component({
  selector: 'app-form-component',
  templateUrl: './form-component.component.html',
  styleUrls: ['./form-component.component.css']
})
export class FormComponent implements OnInit {

  users : User[] = [];

  constructor(private regService : RegisterService, private userService : UserService, private router: Router){}
  
  ngOnInit(): void {
    this.getUsers()
  }

 getUsers(){
   this.userService.getUsers().subscribe(users => this.users = users);
 }
 

  onSubmit(form: NgForm){
    console.log(form)
    const nickname = form.value.nickname
    const email = form.value.email
    const password = form.value.password
    const role="USER"
    
   for(let i = 0; i< this.users.length; i++){
    
      if(this.users[i].nickname == nickname){
        console.log("nickname "+ nickname+ " allready in use")
        return
      }
      else if(!this.users[i].email == email){
        console.log("email "+ email+ " allready in use")  
        return
      }  
    } 
    
    const user = {nickname, email, password, role} as User
          this.regService.addUser(user).subscribe(data => {
            console.log(user)
            console.log(this.users)
        })
        
    
    /* this.userService.saveUser(form) */
    
    this.gotoLogin();
  }
  gotoUsers() {
    console.log("asas1")
    this.router.navigate(['/users']);
  }
  gotoLogin() {
    console.log("asas2")
    this.router.navigate(['/login']);
  }
}

import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from 'src/app/auth/auth.service';
import { User } from 'src/app/interfaces/user';
import { UserService } from 'src/app/users/user.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  users : User[] = [];
  user!: User;

  constructor(private authService : AuthService, private userService : UserService){}


  ngOnInit(): void {
    this.getUsers()
  }

 getUsers(){
   this.userService.getUsers().subscribe(users => this.users = users);
 }



  onSubmit(form: NgForm){
    console.log(form.form.value)
    const email : string = form.value.Email
    const password : string = form.value.password
    let bool : boolean = false

    for(let i = 0; i< this.users.length; i++){
      console.log(typeof(this.users[i].email))
      console.log(typeof(email))
      if(this.users[i].email === email){
        bool = true
        this.user = this.users[i]
        break
      }else{
      console.log("user with email "+email+" is not registered")
      }
    }
    if(bool){
      console.log("asa1")
      if(this.user.password === password){
        this.authService.setAuthenticated()
      } else{
          console.log("password errata")
          return
      }            
    }
  }




}

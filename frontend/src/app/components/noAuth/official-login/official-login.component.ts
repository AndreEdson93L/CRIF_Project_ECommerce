import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-official-login',
  templateUrl: './official-login.component.html',
  styleUrls: ['./official-login.component.css'],
})
export class OfficialLoginComponent {
  username: string = '';
  password: string = '';
  successMessage: string = '';
  errorMessage: string = '';

  constructor(private loginService: LoginService,
    private router : Router, private userService: UserService) {}

  //component method used to call [login of auth service]
  login(): void {
    
    this.errorMessage = '';
    let hasBeenSucces = false
    this.loginService.login(this.username, this.password).subscribe({
      next: (data) => {
        localStorage.clear();
        localStorage.setItem('jwtToken', JSON.stringify(data));

        this.successMessage = "Login successful.. Redirecting!";
        
        const helloJwt = localStorage.getItem('jwtToken');
        
        hasBeenSucces = true;


        this.loginService.loggedIn();

        if(hasBeenSucces){


          this.userService.getUser()

          
          setTimeout(() => {
            this.router.navigate(["/home"])
          }, 2000);
        }   
      },

      error: (err) => {
        console.log('You shall not pass! ');

        // Check the error response from the server, if available
        
        
        if(err.error.status == 500){
          this.errorMessage = "Login failed. Inserted email is not registered.";
        }else if (err.error.status == 401)
        this.errorMessage = "Login failed. Inserted password is not correct.";
        
        setTimeout(() => {
          this.errorMessage = '';
        }, 5000);
      },
    });
  }
}

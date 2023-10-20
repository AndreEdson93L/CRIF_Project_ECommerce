import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-official-login',
  templateUrl: './official-login.component.html',
  styleUrls: ['./official-login.component.css'],
})
export class OfficialLoginComponent {

  @Output() LoginEvent = new EventEmitter<string>();


  username: string = '';
  password: string = '';
  successMessage: string = '';
  errorMessage: string = '';

  isLoggedIn : string = 'false'





  constructor(private loginService: LoginService,
    private router : Router) {}

  //component method used to call [login of auth service]
  login(): void {
    console.log("Login function called");
    
    let hasBeenSucces = false
    this.loginService.login(this.username, this.password).subscribe({
      next: (data) => {
        localStorage.clear();
        localStorage.setItem('jwtToken', JSON.stringify(data));

        this.successMessage = "Login successful.. Redirecting!";
        
        const helloJwt = localStorage.getItem('jwtToken');
        console.log({"jwtToken: ": helloJwt});
        hasBeenSucces = true;

        console.log("hasBeenSuccess: ", hasBeenSucces);

        if(hasBeenSucces){


          this.isLoggedIn = 'true';
          localStorage.setItem('jwtToken', JSON.stringify(data));
          this.loginService.isLoggedIn();

        


          setTimeout(() => {
            this.router.navigate(["/user-details"])
          }, 1000); 



        }   
      },

      error: (err) => {
        console.log('You shall not pass! ');

        // Check the error response from the server, if available
        /* console.log("err: ", err);
        console.log("err.error: ", err.error);
        console.log("err.error.message: ", err.error.message); */
        
        this.errorMessage = "Login failed. Please try again.";
        
        setTimeout(() => {
          this.errorMessage = '';
        }, 5000);
      },
    });
  }
}

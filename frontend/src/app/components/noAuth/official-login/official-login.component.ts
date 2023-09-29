import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-official-login',
  templateUrl: './official-login.component.html',
  styleUrls: ['./official-login.component.css'],
})
export class OfficialLoginComponent {
  username: string = '';
  password: string = '';

  constructor(private loginService: LoginService,
    private router : Router) {}

  //component method used to call [login of auth service]
  login(): void {
    let hasBeeenSucces = false
    this.loginService.login(this.username, this.password).subscribe({
      next: (data) => {
        localStorage.clear()
        
        localStorage.setItem('jwtToken', JSON.stringify(data));
        
        const helloJwt = localStorage.getItem('jwtToken');
        console.log(helloJwt);
        hasBeeenSucces = true
        if(hasBeeenSucces){
          this.router.navigate(["/user-details"])
        }/* else{
          this.router.navigate(["/gandalf-page"])
        } */  
      },
      error: (err) => {
        console.log('You should not pass!', err);
      },
    });
    
  }
}

import { Component } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-official-login',
  templateUrl: './official-login.component.html',
  styleUrls: ['./official-login.component.css'],
})
export class OfficialLoginComponent {
  username: string = '';
  password: string = '';

  constructor(private loginService: LoginService) {}

  //component method used to call [login of auth service]
  login(): void {
    this.loginService.login(this.username, this.password).subscribe({
      next: (data) => {
        console.log('JWT Token: ', data);
        localStorage.setItem('jwtToken', JSON.stringify(data));

        console.log("DAB DAB DUBA DAB DAB DUBA");
        
        const helloJwt = localStorage.getItem('jwtToken');
        console.log(helloJwt);
      },
      error: (err) => {
        console.log('You should not pass!', err);
      },
    });
  }
}

import { Component, OnInit, booleanAttribute } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { OfficialRegistrationService } from 'src/app/services/official-registration.service';
import { User } from 'src/app/models/user';
import { strongPasswordValidator } from 'src/app/utility/validators.util';
import { Router } from '@angular/router';

@Component({
  selector: 'official-registration-form',
  templateUrl: './official-registration-form.component.html',
  styleUrls: ['./official-registration-form.component.css']
})


export class RegisterComponent implements OnInit {

  registerForm!: FormGroup;

  constructor(private fb: FormBuilder, private authService: OfficialRegistrationService, private router: Router) { }

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      nickname: ['', Validators.required],
      password: ['', [Validators.required, strongPasswordValidator]]
    });
  }

  register() {
    let hasBeeenSucces = false
    if (this.registerForm.valid) {
      const user = this.registerForm.value as User;
      this.authService.registerAccount(user).subscribe({
        next: (response) => {
          console.log('User registered:', response);
          hasBeeenSucces = true
          if(hasBeeenSucces){
            this.router.navigate(["/login"])
          }
        },
        error: (err) => {
          console.log('Registration failed:', err);
        }
      });
    }
    
  }  
}

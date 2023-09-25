import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { OfficialRegistrationService } from 'src/app/services/official-registration.service';

@Component({
  selector: 'official-registration-form',
  templateUrl: './official-registration-form.component.html',
  styleUrls: ['./official-registration-form.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;

  constructor(private fb: FormBuilder, private authService: OfficialRegistrationService) { }

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      nickname: ['', Validators.required],
      password: ['', Validators.required],
      role: ['', Validators.required]
    });
  }

  register() {
    if (this.registerForm.valid) {
      this.authService.registerAccount(this.registerForm.value).subscribe(
        response => {
          console.log('User registered:', response);
        },
        error => {
          console.log('Registration failed:', error);
        }
      );
    }
  }
}

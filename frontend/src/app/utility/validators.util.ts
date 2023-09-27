import { FormControl } from '@angular/forms';

// Define the strong password validator as a separate function
export function strongPasswordValidator(control: FormControl) {
  const hasUpperCase = /[A-Z]/.test(control.value);
  const hasLowerCase = /[a-z]/.test(control.value);
  const hasSpecialChar = /[!@#$%^&*]/.test(control.value);
  const hasMinLength = control.value.length >= 8 && control.value.length <= 25;

  if (hasUpperCase && hasLowerCase && hasSpecialChar && hasMinLength) {
    return null;
  } else {
    return { 'weakPassword': true };
  }
}

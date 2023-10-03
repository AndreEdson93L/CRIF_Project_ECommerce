import { CanActivateChildFn, CanActivateFn } from '@angular/router';
import { AuthService } from './auth.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn= (
  route,
  state) => {
  const authService = inject(AuthService);
  authService.setAuthenticated()
  return authService.isAutenticated();
};



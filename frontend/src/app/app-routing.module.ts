import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsersListComponent } from './users/users-list/users-list.component';
import { UserDetailComponent } from './users/users-detail/users-detail.component';
import { FormComponent } from './register/form-component/form-component.component';
import { authGuard, authGuardChild } from './auth/auth.guard';
import { LoginComponent } from './login/login/login.component';

const routes: Routes = [
  {path: 'users', component: UsersListComponent, canActivate: [authGuard], canActivateChild: [authGuardChild], children: [
    /* {path: ':id', component: UserDetailComponent}, */
  ] },
  {path: 'users/:id', component: UserDetailComponent,canActivate: [authGuard]},
  { path: 'register', component:  FormComponent},
  { path: 'login', component:  LoginComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

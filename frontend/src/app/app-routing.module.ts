import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { authGuard, authGuardChild } from './auth/auth.guard';
import { RegisterComponent } from './components/official-registration-form/official-registration-form.component';

const routes: Routes = [
  /*{path: 'users', component: UsersListComponent, canActivate: [authGuard], canActivateChild: [authGuardChild], children: [
     {path: ':id', component: UserDetailComponent}, 
  ] },*/
  { path: 'register', component: RegisterComponent },
  //{ path: 'login', component:  LoginComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

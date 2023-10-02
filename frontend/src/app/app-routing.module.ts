import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { authGuard } from './auth/auth.guard';
import { RegisterComponent } from './components/noAuth/official-registration-form/official-registration-form.component';
import { OfficialLoginComponent } from './components/noAuth/official-login/official-login.component';
import { UserDetailComponent } from './components/user/user-detail/user-detail.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ModifyUserDetailsComponent } from './components/user/modify-user-details/modify-user-details.component';
import { authGuardRole } from './auth/auth-guard-role.guard';
import { AdminUserDetailComponent } from './components/admin/user-detail/user-detail.component';
import { AdminModifyUserDetailComponent } from './components/admin/modify-user-detail/modify-user-detail.component';
import { AllUsersDetailsComponent } from './components/admin/all-users-details/all-users-details.component';

const routes: Routes = [
  /*{path: 'users', component: UsersListComponent, canActivate: [authGuard], canActivateChild: [authGuardChild], children: [
     {path: ':id', component: UserDetailComponent}, 
  ] },*/
  { path: 'home', component: DashboardComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component:  OfficialLoginComponent},
  { path: 'user-details', component:  UserDetailComponent, canActivate: [authGuard]},
  { path: 'modify-user-details', component:  ModifyUserDetailsComponent, canActivate: [authGuard]},
  { path: 'admin-all-users-details', component:  AllUsersDetailsComponent, canActivate: [authGuard , authGuardRole]},
  { path: 'admin-modify-user-details/:email', component:  AdminModifyUserDetailComponent, canActivate: [authGuard]},//
  { path: '*', redirectTo : 'home' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

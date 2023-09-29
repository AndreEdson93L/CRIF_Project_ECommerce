import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms'; 
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { MessagesComponent } from './components/messages/messages.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RegisterComponent } from './components/noAuth/official-registration-form/official-registration-form.component';
import { OfficialLoginComponent } from './components/noAuth/official-login/official-login.component';
import { UserDetailComponent } from './components/user/user-detail/user-detail.component';
import { AuthInterceptorService } from './services/auth-interceptor.service';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ModifyUserDetailsComponent } from './components/user/modify-user-details/modify-user-details.component';
import { AllUsersDetailsComponent } from './components/admin/all-users-details/all-users-details.component';
import { AdminModifyUserDetailComponent } from './components/admin/modify-user-detail/modify-user-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    MessagesComponent,
    NavbarComponent,
    RegisterComponent,
    OfficialLoginComponent,
    UserDetailComponent,
    DashboardComponent,
    ModifyUserDetailsComponent,
    AllUsersDetailsComponent,
    AdminModifyUserDetailComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatButtonModule,
    MatInputModule,
    MatSelectModule,
    BrowserAnimationsModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true },],
  bootstrap: [AppComponent]
})
export class AppModule { }

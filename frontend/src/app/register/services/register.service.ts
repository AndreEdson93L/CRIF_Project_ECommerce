import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { User } from '../../interfaces/user';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { Messageservice } from 'src/app/services/messageservice.service';


@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private usersUrl = 'api/users';  // URL to web api
  
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient ) { }


  addUser(user: User){
    return this.http.post(this.usersUrl, user, this.httpOptions)
  }
  
}
/* 
  users = [
    { id: 1,nickname:'luca', email: 'rossi',password: "pass1", role: "USER"},
    { id: 2,nickname:'ludovica', email: 'bianchi',password: "pass1", role: "USER"},
    { id: 3,nickname:'luna', email: 'neri', password: "pass1", role: "USER"},
    { id: 4,nickname:'lara', email: 'santi',password: "pass1", role: "USER"}
]

  constructor() { }


  getUsers(){
    return this.users
  }
  getUser(index : number){
    for (let i = 0; i < this.users.length; i++) {
      if(this.users[i].id == index){
        return this.users[i];
      }
    }
    return null;
  }
  
  saveUser(form : NgForm){
    console.log("service")
    console.log(form)
    let nickname = form.value.nickname
    let email = form.value.email
    let password = form.value.password
    let role="USER"
    
    let id = this.genId(this.users)
    let user = {id, nickname, email, password, role} as User
    this.users.push(user)
    console.log(this.users)
  }
  
  genId(persone: User[]): number {
    return this.users.length > 0 ? Math.max(...this.users.map(user => user.id)) + 1 : 11;
  
  } */
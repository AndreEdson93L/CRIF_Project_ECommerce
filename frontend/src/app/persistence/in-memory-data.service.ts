import { Injectable } from '@angular/core';
import { User } from '../interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class InMemoryDataService {

  constructor() { }
  createDb() {
    const users = [
      { id: 1,nickname:'luca', email: 'email1@email.com',password: "pass1", role: "USER"},
      { id: 2,nickname:'ludovica', email: 'email2@email.com',password: "pass2", role: "USER"},
      { id: 3,nickname:'luna', email: 'email3@email.com', password: "pass3", role: "USER"},
      { id: 4,nickname:'lara', email: 'email4@email.com',password: "pass4", role: "USER"}
    ];
    return {users};
  }

  genId(users: User[]): number {
    return users.length > 0 ? Math.max(...users.map(user => user.id)) + 1 : 11;
  
  }
}

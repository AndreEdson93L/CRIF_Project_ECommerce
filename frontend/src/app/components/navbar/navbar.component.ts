import { Component, Input, OnChanges } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnChanges{

  @Input()

  //HOW TO CHECK AT EVERY CHANGE THAT LOCALSTORAGE ITEM IS PRESENT? OBSERVABLE?

  public isAdmin = localStorage.getItem('userRole')


  
constructor(private router: Router) { }

  isActive(path: string): boolean {
    return this.router.url === path;
  }

  ngOnChanges(){}
  
}

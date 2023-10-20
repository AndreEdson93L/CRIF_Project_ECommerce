import { Component, OnInit } from '@angular/core';
import {  NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';
import { UrlService } from './services/url.service';
import { LoginService } from './services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'testing-seconda-ese';
  previousUrl: string = '';
  currentUrl: string = '';
  isLoggedIn = false
  isAdmin = false
  hasRegistered = false

  constructor(private router: Router, private urlService : UrlService, private loginService : LoginService) {}




  ngOnInit() {
    this.router.events.pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe((event: any) => {
        this.previousUrl = this.currentUrl;
        this.currentUrl = event.url; 
        this.urlService.setPreviousUrl(this.previousUrl);     
      });          
  }  

}

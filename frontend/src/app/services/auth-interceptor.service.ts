import { HttpClient, HttpEvent, HttpHandler, HttpHandlerFn, HttpHeaders, HttpInterceptor, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment, includedUrls } from 'src/enviroments/enviroments';

@Injectable({
  providedIn: 'root'
})
/* 
export const authenticationInterceptor: HttpInterceptorFn = (req: HttpRequest<unknown>, next:
  HttpHandlerFn) => {
     const userToken = 'MY_TOKEN'; const modifiedReq = req.clone({
       headers: req.headers.set('Authorization', `Bearer ${userToken}`),
     });
  
     return next(modifiedReq);
  }; */
export class AuthInterceptorService implements HttpInterceptor {


  

  constructor(private http: HttpClient) { }

  
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const jwtObj = localStorage.getItem('jwtToken');
    const jwt = jwtObj?.slice(10,length-2)


    
    if (jwt && includedUrls.includes(request.url)) {    
      console.log("inside interceptor modifications");
      const requestWithJwt = request.clone({
        setHeaders: {
          Authorization: `Bearer ${jwt}`
        }
      });
      return next.handle(requestWithJwt);
    }

    


    console.log("sending request");


    return next.handle(request);
  }

  
}

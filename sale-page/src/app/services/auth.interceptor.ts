import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');
    console.log('Intercepting request:', req.url);
    if (token) {
      console.log('Token found, adding Authorization header');
      const clonedRequest = req.clone({
        // headers: req.headers.set('Authorization', `Bearer ${token}`)
        headers: req.headers.set('Authorization', `${token}`)

      });
      return next.handle(clonedRequest);
    }
    console.log('No token found');
    return next.handle(req);
  }

}

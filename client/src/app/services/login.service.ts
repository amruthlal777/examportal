import { HttpClient } from '@angular/common/http';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loginStatusSubject = new Subject<string>();

  constructor(private http : HttpClient) { }

  public getCurrentUser()
  {
    return this.http.get(`${baseUrl}/current-user`);
  }

  public generateToken(loginData : any)
  {
    console.log("token generate");
      return this.http.post(`${baseUrl}/generate-token`,loginData.value);
  }

  public loginUser(token:any)
  {
    localStorage.setItem('token',token);
    return true;
  }

  public isLoggedIn()
  {
    let tokenStr = localStorage.getItem("token");
    if(tokenStr == undefined || tokenStr == '' || tokenStr == null)
    {
      return false;
    }
    else
    {
      return true;
    }
  }

  public logOut()
  {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    return true;
  }

  public getToken()
  {
    return localStorage.getItem('token');
  }

  public setUser(user:any)
  {
    localStorage.setItem('user',JSON.stringify(user));
  }

  public getUser()
  {
    let userStr = localStorage.getItem('user');

    if(userStr != null)
    {
      return JSON.parse(userStr);
    }
    else{
      this.logOut();
      return null;  
    }
  }

  public getUserRole()
  {
    let user = this.getUser();
    return user.authorities[0].authority;
  }
}

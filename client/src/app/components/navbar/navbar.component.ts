import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLoggedIn = false;
  userName:string;

  constructor(public login : LoginService,private router:Router) { }

  ngOnInit(): void {  

    if(typeof this.userName !== 'undefined')
    {
     this.isLoggedIn = true; 
    }
    else
    {
      this.isLoggedIn = false;
    }

    this.login.loginStatusSubject.asObservable().subscribe(data=>{
      this.userName = data;
      if(this.userName !== null)
      {
       this.isLoggedIn = true; 
      }
      else
      {
        this.isLoggedIn = false;
      }
    })
  }

  public logout()
  {   
    this.login.logOut();  
    this.userName = '';
    this.isLoggedIn = false;
    this.router.navigate(['login']);
  }
}

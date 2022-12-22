import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private snack : MatSnackBar,
              private loginService : LoginService,
              private router:Router) { }

  login: FormGroup;

  ngOnInit(){
  this.login = new FormGroup({
    'username':new FormControl(null,Validators.required),
    'password':new FormControl(null,Validators.required),
  });
  }

  onSubmit()
  {
    if(this.login.value.username.trim() == '' || this.login.value.username == null)
    {
      this.snack.open("Username is required ",'',{
        duration:3000,
        verticalPosition : 'top',
      })

      return;
    }

    if(this.login.value.password.trim() == '' || this.login.value.password == null)
    {
      this.snack.open("password is required ",'',{
        duration:3000,
        verticalPosition : 'top',
      })

      return;
    }

    this.loginService.generateToken(this.login).subscribe(
      (data:any)=>{
        console.log("success");
        console.log(data);
          this.loginService.loginUser(data.token);

          this.loginService.getCurrentUser().subscribe(
            (user:any)=>{
              this.loginService.setUser(user);
              console.log("loggin "+user); 
              this.loginService.loginStatusSubject.next(user.username);

              console.log("role "+this.loginService.getUserRole());

              //re direct based on the role
              if(this.loginService.getUserRole() == 'ADMIN')
              {
                this.router.navigate(['/admin']);
              }else if(this.loginService.getUserRole() == 'NORMAL')
              {
                this.router.navigate(['/user-dashboard/0']);
              }else{
                this.loginService.logOut();
                location.reload();
              }
            }
          );

      },
      (error)=>{
        console.log("error");
        this.snack.open('Invalid details !!. Try again','',{
          duration:3000
        });
      }
    )
  }
}

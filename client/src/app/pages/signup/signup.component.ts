import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userService: UserService, private http: HttpClient
    , private _snack: MatSnackBar,private _router:Router) { };

  user: FormGroup;

  ngOnInit() {

    this.user = new FormGroup({
      'username': new FormControl(null, Validators.required),
      'password': new FormControl(null, Validators.required),
      'firstName': new FormControl(null, Validators.required),
      'lastName': new FormControl(null),
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'phone': new FormControl(null, Validators.required),
    });
  }

  onSubmit() {
    console.log(this.user);

    if (this.user.value.username == '' || this.user.value.username == null) {
      this._snack.open('Username is required !!', '', {
        duration: 3000,
        verticalPosition: 'top',
        //  horizontalPosition:'right'
      });
      return;
    }

    this.userService.addUser(this.user).subscribe(
      (data: any) => {

        Swal.fire('Success', 'User is created', 'success').then(
        ()=>{
          
          this._router.navigate(['login']);
        }
        );
      },
      (error) => {
        this._snack.open("something went wrong", '', {
          duration: 3000,
          verticalPosition: 'top',
        });
      }
    );

  }
}

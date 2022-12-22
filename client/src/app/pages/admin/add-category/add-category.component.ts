import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {

  category ={
    title : '',
    description : '',
  }
  constructor(private _categorySvc:CategoryService,private _snack:MatSnackBar,
    private router:Router) { }

  ngOnInit(): void {
  }

  formSubmit()
  {
    if(this.category.title.trim() == '' || this.category.description.trim() == '')
    {
      this._snack.open("Title required",'',{
        duration:3000,
      });
    }
    
    this._categorySvc.addCategory(this.category).subscribe(
      (data)=>{
          Swal.fire('Success','category added successfully').then(()=>{
            this.router.navigateByUrl('/admin/categories'); 
          })
      },
      (error)=>{
        console.log(error);
      }
    );
  }
}

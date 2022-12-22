import { Component, OnInit } from '@angular/core';
import { CategoryService } from 'src/app/services/category.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-categories',
  templateUrl: './view-categories.component.html',
  styleUrls: ['./view-categories.component.css']
})
export class ViewCategoriesComponent implements OnInit {

  categories:any=[];
  constructor(private _category:CategoryService) { }

  ngOnInit(): void {

    this._category.categories().subscribe(
    (data:any) => {
      
      this.categories=data;

    },
    (error)=>{

      console.log(error);
      Swal.fire('Error !!','Error in loading data','error');
    }
    ); 
  }

  public deleteCategory(cid:any)
  {
        Swal.fire({
          title: 'Are you sure want to Delete?',
          text: 'This category will be no longer available!',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonText: 'Yes, delete it!',
          cancelButtonText: 'No, keep it'
        }).then((result) => {
          if (result.value) {
            this._category.deleteCategory(cid).subscribe(
              (data)=>{
                Swal.fire(
                  'Deleted!',
                  'Category has been deleted.',
                  'success'
                ).then(()=>{window.location.reload();})
              },(error)=>{
                console.log(error);
              }
            );
            
          } else if (result.dismiss === Swal.DismissReason.cancel) {
            Swal.fire(
              'Cancelled',
              'Operation cancelled :)',
              'error'
            )
          }
        });       
 
  }

}

import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';

@Component({
  selector: 'app-update-quiz',
  templateUrl: './update-quiz.component.html',
  styleUrls: ['./update-quiz.component.css']
})
export class UpdateQuizComponent implements OnInit {

public Editor = ClassicEditor;

constructor(private categoryService:CategoryService , 
    private _snack:MatSnackBar,
    private quizService:QuizService,
    private router:Router,
    private route:ActivatedRoute) { }

categories:any=[];
qId = null;
quiz:any;

ngOnInit(): void {

  this.qId = this.route.snapshot.params?.['qId'];

  this.quizService.getQuiz(this.qId).subscribe(
    (data)=>{
      this.quiz = data;
    }
  );

  this.categoryService.categories().subscribe(
    (data)=>{
    this.categories = data;
    }
  );  

}

updateQuiz()
  {
    if(this.quiz.title.trim()=='' || this.quiz.title==null)
    {
      this._snack.open('Title is mandatory','',{
        duration:3000,
      });
      return;
    }
    
    if(this.quiz.category.cid =='' || this.quiz.category.cid==null)
    {
      this._snack.open('Category is mandatory','',{
        duration:3000,
      });
      return;
    }

    this.quizService.updateQuiz(this.quiz).subscribe(
      (data:any)=>{
        Swal.fire('Success','Quiz is Updated','success').then(()=>{
          this.router.navigateByUrl('/admin/quizzes');
        }
        );       
      },
      (error)=>{
        Swal.fire('Error!!','Error while updating quiz','error');
      }
    );
  }

}

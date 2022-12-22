import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';

@Component({
  selector: 'app-add-quiz',
  templateUrl: './add-quiz.component.html',
  styleUrls: ['./add-quiz.component.css']
})
export class AddQuizComponent implements OnInit {

  public Editor = ClassicEditor;

  constructor(private categoryService:CategoryService , 
              private _snack:MatSnackBar,
              private quizService:QuizService,
              private router:Router) { }

  categories:any=[];

  quizData={
    title:'',
    description:'',
    maxMarks:'',
    numberOfQuestions:'',
    active:true,
    instruction:'',
    negMarking:'',
    category:{
      cid:'',
    },
    qid:''
  }
  ngOnInit(): void {

    this.categoryService.categories().subscribe(
      (data)=>{
        this.categories = data;
      }
    );
  }

  addQuiz()
  {
    if(this.quizData.title.trim()=='' || this.quizData.title==null)
    {
      this._snack.open('Title is mandatory','',{
        duration:3000,
      });
      return;
    }
    
    if(this.quizData.category.cid =='' || this.quizData.category.cid==null)
    {
      this._snack.open('Category is mandatory','',{
        duration:3000,
      });
      return;
    }

    this.quizService.addQuizzes(this.quizData).subscribe(
      (data:any)=>{
        Swal.fire('Success','Quiz is added','success').then(()=>{
          this.router.navigateByUrl('/admin/quizzes');
        }
        );       
      },
      (error)=>{
        Swal.fire('Error!!','Error while adding quiz','error');
      }
    );
  }

}

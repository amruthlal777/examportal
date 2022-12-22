import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';

@Component({
  selector: 'app-update-question',
  templateUrl: './update-question.component.html',
  styleUrls: ['./update-question.component.css']
})
export class UpdateQuestionComponent implements OnInit {
  quesId:any;
  title:any;
  qid:any;
  public Editor = ClassicEditor;

  question:any=null;

  constructor(private _route:ActivatedRoute,
              private questionService:QuestionService,
              private _snack:MatSnackBar,
              private router:Router) { }

  ngOnInit(): void {
    
    this.quesId = this._route.snapshot.params?.['quesId'];
    this.title = this._route.snapshot.params?.['title'];
    this.qid = this._route.snapshot.params?.['qid'];

    this.questionService.getQuestion(this.quesId).subscribe(
      (data)=>{
        this.question = data;
      },
      (error)=>{
        this._snack.open('Error fetching question','',{
          duration:3000,
        });
      }
    );
  }

  formSubmit()
  {
    this.questionService.updateQuestion(this.question).subscribe(
      (data)=>{
          Swal.fire('Success','Question updated successfully','success').then(
            ()=>{
              this.router.navigateByUrl('/admin/view-questions/'+this.qid+'/'+this.title);
            }
          );
      },
      (error)=>{
          this._snack.open('Error updating question','',{
            duration:3000,
          });
      }
    );
  }
}

import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quiz-questions',
  templateUrl: './view-quiz-questions.component.html',
  styleUrls: ['./view-quiz-questions.component.css']
})
export class ViewQuizQuestionsComponent implements OnInit {

  questions:any = [];
  id:any;
  title:any;
  isInstructionClicked:boolean=false;

  constructor(private route:ActivatedRoute,
              private questionService:QuestionService,
              private _snack:MatSnackBar) { }

  ngOnInit(): void {

    this.title = this.route.snapshot.params?.['title'];
    this.id = this.route.snapshot.params?.['id'];

    this.questionService.getQuestions(this.id).subscribe(
      (data)=>{
        this.questions = data;
      },
      (error)=>{
        console.log("error fetching questions");
      }
    );
  }

  deleteQuestion(quesId:any)
  {

    Swal.fire({
      title: 'Are you sure want to Delete?',
      text: 'This Question will be no longer available!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
      cancelButtonText: 'No, keep it'
    }).then((result) => {
      if (result.value) {
        this.questionService.deleteQuestion(quesId).subscribe(
          (data)=>{
           
            this.questions = this.questions.filter((question:any)=>question.quesId != quesId);
            this._snack.open('Question deleted successfully!','',{
              duration:3000,
            });
          },(error)=>{
            this._snack.open('Error deleting question','',{
              duration:3000,
            });
            
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

  instructionClicked()
  {
    this.isInstructionClicked = true;
  }
}

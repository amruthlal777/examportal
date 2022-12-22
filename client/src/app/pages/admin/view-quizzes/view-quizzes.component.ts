import { Component, OnInit } from '@angular/core';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-quizzes',
  templateUrl: './view-quizzes.component.html',
  styleUrls: ['./view-quizzes.component.css']
})
export class ViewQuizzesComponent implements OnInit {

  quizzes:any=[];

  constructor(private quizService:QuizService) { }

  ngOnInit(): void {

   this.quizService.getQuizzes().subscribe(
      (data)=>{
        this.quizzes = data;
      },
      (error)=>{
        console.log(error);
      }
    );
  }

 deleteQuiz(qId:any)
 {
  
  Swal.fire({
    title: 'Are you sure want to Delete?',
    text: 'This Quiz will be no longer available!',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Yes, delete it!',
    cancelButtonText: 'No, keep it'     
  }).then((result) => {
    if (result.value) {
      this.quizService.deleteQuiz(qId).subscribe(
        (data)=>{
          this.quizzes = this.quizzes.filter((quiz:any)=>quiz.qid != qId);
          Swal.fire(
            'Deleted!',
            'Quiz has been deleted.',
            'success'
          )
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

 updateQuiz(quiz:any)
 {
   this.quizService.updateQuiz(quiz).subscribe(
    (data)=>{
      Swal.fire('Success','Quiz is updated is successfully','success');
    }
   );
 }
}

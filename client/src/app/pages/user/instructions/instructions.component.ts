import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-instructions',
  templateUrl: './instructions.component.html',
  styleUrls: ['./instructions.component.css']
})
export class InstructionsComponent implements OnInit {

  qid:any;
  quiz:any;

  constructor(private route:ActivatedRoute,
              private quizService:QuizService,
              private router:Router) { }

  ngOnInit(): void {

    this.qid = this.route.snapshot.params?.['qid'];
    
    this.quizService.getQuiz(this.qid).subscribe(
      (data)=>{
        this.quiz = data;
      },
      (error)=>{
        console.log("Error in loading quiz data");
        alert("Error in loading quiz data");
      }
    );
  }

  startQuiz()
  {
    Swal.fire({
      title: 'Are you sure you want to start the quiz?',
      text: 'Once the quiz is started, you cannot pause it!',
      icon: 'info',
      showCancelButton: true,
      confirmButtonText: 'Yes, start it!',
      cancelButtonText: 'No'     
    }).then((result) => {
      if (result.value) {
        this.router.navigateByUrl('/start/'+this.qid);
      } 
    });  
  }
}

import { LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-start-quiz',
  templateUrl: './start-quiz.component.html',
  styleUrls: ['./start-quiz.component.css']
})
export class StartQuizComponent implements OnInit {

  qid:any;
  questions:any =[];
  isSubmitted:boolean = false;
  value = 50;
  timer:any;
  evalData:any;

  constructor(private location:LocationStrategy,
              private route:ActivatedRoute,
              private questionService:QuestionService) { }

  ngOnInit(): void {

    this.preventBackButton();

    this.qid = this.route.snapshot.params?.['qid'];

    this.questionService.getQuestionsForQuiz(this.qid).subscribe(
      (data)=>{
        this.questions = data;

        this.timer = this.questions.length * 2 * 60;
    
        this.startTimer();

      }
    );

   
  }

  preventBackButton()
  {
    history.pushState(null,'',location.href);

    this.location.onPopState(()=>{
      history.pushState(null,'',location.href);
    });
  }

  submitQuiz()
  {
    Swal.fire({
      title:'Do you want to submit the quiz?',
      showCancelButton:true,
      confirmButtonText:'Submit',
      icon:'info',
    }).then((e)=>{
      if(e.isConfirmed){
        this.evalQuiz();
      }
    });
  }

  evalQuiz()
  {
    this.isSubmitted = true;
    this.questionService.evalQuiz(this.questions).subscribe(
      (data)=>{
        console.log("data send for evaluation");
        this.evalData = data;
      },
      (error)=>{
        console.log("error sending data for evaluation");
      }
    );
  }

  startTimer()
  {
    let t:any = window.setInterval(()=>{
      if(this.timer<=0)
      {
        this.evalQuiz();
        clearInterval(t);
      }else{
        this.timer--;
      }
    },1000);
  }

  getFormattedTime()
  {
    let mm = Math.floor(this.timer/60);
    let ss = this.timer-mm*60;

    return `${mm} min : ${ss} sec`;
  }
}

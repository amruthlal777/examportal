import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuizService } from 'src/app/services/quiz.service';

@Component({
  selector: 'app-load-quiz',
  templateUrl: './load-quiz.component.html',
  styleUrls: ['./load-quiz.component.css']
})
export class LoadQuizComponent implements OnInit {

  catId:any;
  quizzes:any=[];

  constructor(private route:ActivatedRoute,
              private quizService:QuizService) { }

  ngOnInit(): void {
    this.catId = this.route.snapshot.params?.['catId'];

    this.route.params.subscribe(
      (params)=>{
        this.catId = params?.['catId'];
      
        if(this.catId == 0)
        {
          this.quizService.getActiveQuizzes().subscribe(
            (data)=>{
              this.quizzes=data;
            }
          );
        }
        else{
          
          this.quizzes = this.quizService.getQuizByCategoryActive(this.catId).subscribe(
            (data)=>{
              this.quizzes= data;
            }
          );
        }
      }
    );
  }
}

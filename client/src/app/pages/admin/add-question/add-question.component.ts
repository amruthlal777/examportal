import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import Swal from 'sweetalert2';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {

  public Editor = ClassicEditor;

  qId:any;
  title:any;
  
  question:any={
    quiz:{},
    content:'',
    option1:'',
    option2:'',
    option3:'',
    option4:'',
    answer:'',
  };
  constructor(private route:ActivatedRoute,
              private questionService:QuestionService,
              private router:Router) 
  {}

  ngOnInit(): void {

    this.qId = this.route.snapshot.params?.['id'];
    this.title = this.route.snapshot.params?.['title'];

    this.question.quiz['qid'] = this.qId;
  }

  formSubmit()
  {
    this.questionService.addQuestion(this.question).subscribe(
      (data)=>{
        Swal.fire('Success','Question Added Successfully','success').then(
          ()=>{
            this.router.navigateByUrl('/admin/view-questions/'+this.qId+'/'+this.title);
          }
        );
      }
    );
  }
}

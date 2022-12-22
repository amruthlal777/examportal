import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private _http:HttpClient) { }

  public getQuestions(id:any)
  {
    return this._http.get(`${baseUrl}/question/quiz/all/${id}`); 
  }

  public getQuestionsForQuiz(id:any)
  {
    return this._http.get(`${baseUrl}/question/quiz/${id}`); 
  }

  public addQuestion(question:any)
  {
    return this._http.post(`${baseUrl}/question/`,question); 
  }

  deleteQuestion(id:any)
  {
    return this._http.delete(`${baseUrl}/question/${id}`); 
  }

  updateQuestion(question:any)
  {
    return this._http.put(`${baseUrl}/question/`,question); 
  }

  public getQuestion(id:any)
  {
    return this._http.get(`${baseUrl}/question/${id}`); 
  }

  public evalQuiz(questions:any)
  {
    return this._http.post(`${baseUrl}/question/eval-quiz`,questions);
  }
}

	package com.exam.examMS.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examMS.entity.exam.Question;
import com.exam.examMS.entity.exam.Quiz;
import com.exam.examMS.service.QuestionService;
import com.exam.examMS.service.QuizService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

	private final QuestionService questionService;
	private final QuizService quizService;
	
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question)
	{
		System.out.println("question "+question.toString());
		Question question2 = this.questionService.addQuestion(question);
		return ResponseEntity.ok(question2);
	}
	
	@GetMapping("/{questionId}")
	public Question getQuestion(@PathVariable("questionId") Long questionId)
	{
		return this.questionService.getQuestion(questionId);
	}
	
	@GetMapping("/quiz/{quizId}")
	public ResponseEntity<?> getQuestionForQuiz(@PathVariable("quizId") Long quizId)
	{
		
		Quiz quiz = quizService.getQuiz(quizId);
		
		Set<Question> questions = quiz.getQuestions();
		
		List<Question> list = new ArrayList<Question>(questions);
		
		if(list.size() > Integer.parseInt(quiz.getNumberOfQuestions()))
		{
			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions()+1));
		}
			
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/quiz/all/{quizId}")
	public ResponseEntity<?> getQuestionForQuizAdmin(@PathVariable("quizId") Long quizId)
	{
		
		Quiz quiz = quizService.getQuiz(quizId);
		
		Set<Question> questions = quiz.getQuestions();
		
		List<Question> list = new ArrayList<Question>(questions);
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getQuestions()
	{
		return ResponseEntity.ok(this.questionService.getQuestion());
	}
	
	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question)
	{
		Question question2 = this.questionService.updateQuestion(question);
		return ResponseEntity.ok(question2);
	}
	
	@DeleteMapping("/{questionId}")
	public void deleteQuestion(@PathVariable("questionId") Long questionId)
	{
		this.questionService.deleteQuestion(questionId);
	}
	
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions)
	{

		double marksGot = 0;
		int correctAnswers = 0;
		int attempted = 0;
		
		
		// User given answers with question id - Map
	//	Map<Long,String> answerMap = questions.stream().collect(Collectors.toMap(Question::getQuesId, Question::getGivenAnswer));
						
		Map<Long,String> answerMap = questions.stream()
		        .collect(HashMap<Long,String>::new, (m,v)->m.put(v.getQuesId(), v.getGivenAnswer()), HashMap::putAll);
		
		System.out.println(answerMap);
		//Fetching the answers for each question from database
//		Map<Long,String> dbAnswerMap =  questionService.getQuestionsForEval(answerMap.keySet()).stream()
//				.collect(Collectors.toMap(Question::getQuesId, Question::getAnswer));

		Map<Long,String> dbAnswerMap = questionService.getQuestionsForEval(answerMap.keySet()).stream()
		        .collect(HashMap<Long,String>::new, (m,v)->m.put(v.getQuesId(), v.getAnswer()), HashMap::putAll);
		
		System.out.println(dbAnswerMap);
		
		double markSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
		
		for(Long quesId:answerMap.keySet())
		{	
			if(answerMap.get(quesId) == null || answerMap.get(quesId) == "")
			{
				continue;
			}
			else
			{
				attempted++;
			}
			
			if(answerMap.get(quesId).equals(dbAnswerMap.get(quesId)))
			{
				correctAnswers++;
				
				marksGot += markSingle; 
			}
			
		}
		
		Map<Object,Object> map = Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);
		
		return ResponseEntity.ok(map);
	}
	
}

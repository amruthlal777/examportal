package com.exam.examMS.controller;

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

import com.exam.examMS.entity.exam.Category;
import com.exam.examMS.entity.exam.Quiz;
import com.exam.examMS.service.QuizService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

	private final QuizService quizService;
	
	@PostMapping("/")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz)
	{
		System.out.println("quiz "+quiz.toString());
		Quiz quiz2 = this.quizService.addQuiz(quiz);
		return ResponseEntity.ok(quiz2);
	}
	
	@GetMapping("/{quizId}")
	public Quiz getQuiz(@PathVariable("quizId") Long quizId)
	{
		return this.quizService.getQuiz(quizId);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getQuizes()
	{
		return ResponseEntity.ok(this.quizService.getQuiz());
	}
	
	@PutMapping("/")
	public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz)
	{
		Quiz quiz2 = this.quizService.updateQuiz(quiz);
		return ResponseEntity.ok(quiz2);
	}
	
	@DeleteMapping("/{quizId}")
	public void deleteQuiz(@PathVariable("quizId") Long quizId)
	{
		this.quizService.deleteQuiz(quizId);
	}
	
	@GetMapping("/category/{catId}")
	public Set<Quiz> getQuizByCategory(@PathVariable("catId") Long catId)
	{
		Category category = new Category();
		category.setCid(catId);
		
		return this.quizService.getQuizByCategory(category);
	}
	
	@GetMapping("/active")
	public Set<Quiz> getActiveQuizzes()
	{
		return this.quizService.getActiveQuizzes();
	}
	
	@GetMapping("/category/active/{catId}")
	public Set<Quiz> getQuizByCategoryActive(@PathVariable("catId") Long catId)
	{
		Category category = new Category();
		category.setCid(catId);
		
		return this.quizService.getActiveQuizzesOfCategory(category);
	}
	
	
}
																																		
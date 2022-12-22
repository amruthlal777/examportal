package com.exam.examMS.service;

import java.util.Set;

import com.exam.examMS.entity.exam.Category;
import com.exam.examMS.entity.exam.Quiz;

public interface QuizService {


	public Quiz addQuiz(Quiz quiz);
	public Quiz updateQuiz(Quiz quiz);
	public Set<Quiz> getQuiz();
	public Quiz getQuiz(Long quizId);
	public Set<Quiz> getQuizByCategory(Category category);
	public Set<Quiz> getActiveQuizzes();
	public Set<Quiz> getActiveQuizzesOfCategory(Category category);
	public void deleteQuiz(Long quizId);
}

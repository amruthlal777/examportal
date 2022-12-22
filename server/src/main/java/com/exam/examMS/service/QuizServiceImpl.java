package com.exam.examMS.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.examMS.entity.exam.Category;
import com.exam.examMS.entity.exam.Quiz;
import com.exam.examMS.repo.QuizRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

	private final QuizRepository quizRepository;
	@Override
	public Quiz addQuiz(Quiz quiz) {

		return this.quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		
		return this.quizRepository.save(quiz);
	}

	@Override
	public Set<Quiz> getQuiz() {

		return new LinkedHashSet<Quiz>(this.quizRepository.findAll());
	}

	@Override
	public Quiz getQuiz(Long quizId) {
	
		return this.quizRepository.findById(quizId).get();
	}

	@Override
	public Set<Quiz> getQuizByCategory(Category category) {
	
		return this.quizRepository.findByCategory(category);
	}
	
	@Override
	public void deleteQuiz(Long quizId) {
		
		this.quizRepository.deleteById(quizId);	
	}

	@Override
	public Set<Quiz> getActiveQuizzes() {
		return this.quizRepository.findByActive(true);
	}

	@Override
	public Set<Quiz> getActiveQuizzesOfCategory(Category category) {
		return this.quizRepository.findByCategoryAndActive(category, true);
	}

}

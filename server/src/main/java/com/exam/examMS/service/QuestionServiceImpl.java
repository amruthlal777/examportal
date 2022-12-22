package com.exam.examMS.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.examMS.entity.exam.Question;
import com.exam.examMS.entity.exam.Quiz;
import com.exam.examMS.repo.QuestionRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService{

	private final QuestionRepository questionRepository;
	
	@Override
	public Question addQuestion(Question question) {
	
		return this.questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		
		return this.questionRepository.save(question);		
	}

	@Override
	public Set<Question> getQuestion() {

		return new LinkedHashSet<>(this.questionRepository.findAll());
	}

	@Override
	public Question getQuestion(Long questionId) {
		
		return this.questionRepository.findById(questionId).get();
	}

	@Override
	public void deleteQuestion(Long questionId) {
		
		this.questionRepository.deleteById(questionId);
		
	}

	@Override
	public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
		
		return this.questionRepository.findByQuiz(quiz);
	}

	@Override
	public List<Question> getQuestionsForEval(Set<Long> questionIdList) {

		List<Question> quesList = questionRepository.findByQuesIdIn(questionIdList);
		
		return quesList;
	}

}

package com.exam.examMS.service;

import java.util.List;
import java.util.Set;

import com.exam.examMS.entity.exam.Question;
import com.exam.examMS.entity.exam.Quiz;

public interface QuestionService {

	public Question addQuestion(Question question);
	public Question updateQuestion(Question question);
	public Set<Question> getQuestion();
	public Question getQuestion(Long questionId);
	public Set<Question> getQuestionsOfQuiz(Quiz quiz);
	public void deleteQuestion(Long questionId);
	public List<Question> getQuestionsForEval(Set<Long> questionIdList);
}

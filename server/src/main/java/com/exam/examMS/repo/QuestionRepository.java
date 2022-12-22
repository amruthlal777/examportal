package com.exam.examMS.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.examMS.entity.exam.Question;
import com.exam.examMS.entity.exam.Quiz;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

	Set<Question> findByQuiz(Quiz quiz);

	List<Question> findByQuesIdIn(Set<Long> questionIdList);

}

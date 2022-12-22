package com.exam.examMS.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.examMS.entity.exam.Category;
import com.exam.examMS.entity.exam.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>{
	
	Set<Quiz> findByCategory(Category category);
	
	Set<Quiz> findByActive(Boolean b);
	
	Set<Quiz> findByCategoryAndActive(Category category,Boolean b);

}

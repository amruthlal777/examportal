package com.exam.examMS.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.examMS.entity.exam.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}

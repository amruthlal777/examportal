package com.exam.examMS.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.examMS.entity.exam.Category;
import com.exam.examMS.repo.CategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{

	private final CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category category) {
	
		return this.categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		
		return this.categoryRepository.save(category);
	}

	@Override
	public Set<Category> getCategory() {
	
		return new LinkedHashSet<Category>(this.categoryRepository.findAll());
	}

	@Override
	public Category getCategory(Long categoryId) {
		
		return this.categoryRepository.findById(categoryId).get();
	}

	@Override
	public void deleteCategory(Long categoryId) {
		
		this.categoryRepository.deleteById(categoryId);
		
	}

}

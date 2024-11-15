package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoryDto;

@Service
public interface CategoryService {

	//create 
	
	 CategoryDto createCategory(CategoryDto categoryDto);
	
	// update
	 
	 CategoryDto updateCategory( CategoryDto categoryDto, Integer categoryId);
	 
	 
	
	// get
	 CategoryDto getCategoryById(Integer catgoryId);
    	 
	List<CategoryDto> getAllCategory();

	// delete
		 
	void deleteCategoryById(Integer categoryId);
	
	
	
	
}

package com.example.demo.serviceImplement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;

@Service
public class CategoryServiceImplement implements CategoryService {
	
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		
		if (categoryDto == null) {
            throw new IllegalArgumentException("Category data cannot be null");
        }
		
		Category category = modelMapper.map(categoryDto, Category.class);
		
		Category addCategory = categoryRepository.save(category);
		
		return modelMapper.map(addCategory, CategoryDto.class); 
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryID", categoryId));
				
		existingCategory.setCategoryTitle(categoryDto.getCategoryTitle());
		existingCategory.setCategoryDescpription(categoryDto.getCategoryDescpription());
		
	         Category updateCategory =      categoryRepository.save(existingCategory);
	         
	         return modelMapper.map(updateCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "categoryid", categoryId));
		
		return modelMapper.map(category, CategoryDto.class);
	}

	
	
	@Override
	public List<CategoryDto> getAllCategory() {
	    List<Category> categories = categoryRepository.findAll();
	    List<CategoryDto> categoriesDto = categories.stream()
	        .map(cat -> modelMapper.map(cat, CategoryDto.class)) // Map each category individually
	        .collect(Collectors.toList());

	    System.out.println(categoriesDto);
	    return categoriesDto;
	}

	@Override
	public void deleteCategoryById(Integer categoryId) {
		
		
		categoryRepository.deleteById(categoryId);
		
	
	}

}

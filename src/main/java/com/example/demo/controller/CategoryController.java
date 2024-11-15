package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategoryDto;
import com.example.demo.payload.ApiResponse;
import com.example.demo.service.CategoryService;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;

	
	@PostMapping("/")
	ResponseEntity<ApiResponse<CategoryDto>>  createCategory(@RequestBody  CategoryDto categoryDto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("201",
				"success",
				"Category Created Succesfully",
				categoryService.createCategory(categoryDto)));
		
	}
	
	
	@PutMapping("/{categoryId}")
	ResponseEntity<ApiResponse<CategoryDto>>  updateCategory(@Valid 
			@RequestBody CategoryDto categoryDto,@PathVariable  Integer categoryId){
		
		return ResponseEntity.status(HttpStatus.CREATED).body( new ApiResponse<>("200","success",
				"Category succesfully updated",
				categoryService.updateCategory(categoryDto, categoryId)));
	}
	
	
	@GetMapping("/{categoryId}")
	ResponseEntity<ApiResponse<CategoryDto>>  getCategoryById(@PathVariable  Integer categoryId){
		return ResponseEntity.status(HttpStatus.CREATED).body( new ApiResponse<>("200","success",
				"Category succesfully updated",
				categoryService.getCategoryById(categoryId)));
	}
	
	
	@GetMapping("/")
	ResponseEntity<ApiResponse<List<CategoryDto>>>  getAllCategory(){
		return ResponseEntity.status(HttpStatus.CREATED).body( new ApiResponse<>("200","success",
				"Category successfully fetched",
				categoryService.getAllCategory()));
	}
	
	
	@DeleteMapping("/")
	ResponseEntity<ApiResponse<String>> deleteCategoryById(@PathVariable  Integer categoryId){
		return ResponseEntity.status(HttpStatus.CREATED).body( new ApiResponse<>("200","success",
				"Employee deleted Succesfully",
				null));
		
	}
	
	
}

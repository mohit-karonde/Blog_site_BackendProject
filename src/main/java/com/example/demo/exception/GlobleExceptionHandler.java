package com.example.demo.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.payload.ErrorResponse;

@RestControllerAdvice
public class GlobleExceptionHandler {
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<ErrorResponse> methodArgumentNotValidException( MethodArgumentNotValidException ex){
		

        List<Map<String, String>> fieldErrors = new ArrayList<>();
        
        // Collect each field error in its own map
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put(error.getField(), error.getDefaultMessage());
            fieldErrors.add(errorMap);
        }
		  
		
		ErrorResponse response = new ErrorResponse("400","Please Re-check Given input, provided data is not in excepted formate",fieldErrors.toString());
		
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	ResponseEntity<ErrorResponse> resourceNotFoundException(ResourceNotFoundException ex){
		
		ErrorResponse response = new ErrorResponse("404","user with not id not found", ex.toString());
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(RuntimeException.class)
	ResponseEntity<ErrorResponse>  runtimeExceptionHandler(RuntimeException ex){
		
		ErrorResponse response = new ErrorResponse("404","user with id not found ", ex.toString());
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}

	
	
}

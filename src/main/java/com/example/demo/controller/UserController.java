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

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/")
	ResponseEntity<UserDto> creteUser(@Valid @RequestBody UserDto userDto){
		UserDto newUserDto = userService.creteUser(userDto);
		return new  ResponseEntity<>(newUserDto,HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/{id}")
	ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
	 
		
		return new ResponseEntity<>(userService.getUserById(id),HttpStatus.FOUND);
	
	}
	
	@PutMapping("/{id}")
	ResponseEntity<UserDto> updateUserById(@Valid @RequestBody UserDto userDto,@PathVariable Integer id){
		
		return new ResponseEntity<>(userService.updateUserById(userDto, id),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteUserById(@PathVariable Integer id){ 
		userService.deleteUserById(id);
		
		return new ResponseEntity<>("user deleted succesfully",HttpStatus.OK);
	}
	
	@GetMapping("/")
    ResponseEntity<List<UserDto>> getAllUser(){
		
		return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
		
	}
	

}

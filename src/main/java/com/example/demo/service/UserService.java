package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;



@Service
public interface UserService {

	
	UserDto creteUser(UserDto userDto);
	
	UserDto getUserById(Integer id);
	
	UserDto updateUserById(UserDto user, Integer id);
	
	
	List<UserDto> getAllUser();
	
	void deleteUserById(Integer id);
	
	
}


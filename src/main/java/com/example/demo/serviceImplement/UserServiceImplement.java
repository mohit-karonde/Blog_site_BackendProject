package com.example.demo.serviceImplement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImplement implements UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public UserDto creteUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		
		User savedUser = repository.save(user);
		
		return this.userToUserDto(savedUser);
	}

	@Override
	public UserDto getUserById(Integer id) {
		// TODO Auto-generated method stub
		User user = repository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
		
		System.out.println(repository.getClass().getPackageName());
		System.out.println(repository.getClass().getName());
		return userToUserDto(user);
	}

	@Override
	public UserDto updateUserById(UserDto userDto, Integer id) {
		User user = repository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		
        return userToUserDto(user)	;	

	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = repository.findAll();
		
		List<UserDto> userDto = users.stream().map(user -> userToUserDto(user)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUserById(Integer id) {
		
		repository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
		
		repository.deleteById(id);
		
	}
	
	private User dtoToUser (UserDto userDto) {
		
		User Newuser = modelmapper.map(userDto, User.class);
//		Newuser.setId(user.getId());
//		Newuser.setName(user.getName());
//		Newuser.setEmail(user.getEmail());
//		Newuser.setAbout(user.getAbout());
//		Newuser.setPassword(user.getPassword());
//		
		return Newuser;
		
	}
	
	private UserDto userToUserDto(User user) {
		
		UserDto userDto = modelmapper.map(user, UserDto.class); 
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		return userDto;
		
	}

}

package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	

	 private int id;
	 
	 @NotNull
	 @Size(min=3 ,max = 15, message = "Name should have min 3 character and max 10")
	 private String name;
	 
	 @Email
	 @Size( message = "Please follow proper system example: mohitkaronde@gmail.com")
	 private String email;
	 
	 @NotNull
	 @Size(min=8 ,max = 30, message = "Password should have min 8 character and max 30")
	 private String password;
	 
	 @NotNull
	 @Size(min=8 , max=30 , message ="about should have min 10 words and max 50 words")
	 private String about;

}

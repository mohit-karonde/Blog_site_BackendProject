package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	
	
    private Integer categoryId;

    @NotNull
    @Size(min=5,max=20,message="categoryTitle should have min 5 and max 20 char")
	private String categoryTitle;
	
	@NotNull
	@Size(min=10,max=50, message="category Description should have min 5 and max 20 char")
	private String categoryDescpription;
	

}

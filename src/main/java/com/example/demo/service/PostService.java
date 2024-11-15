package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PostDto;
import com.example.demo.payload.PostResponse;

@Service
public interface PostService {

	
	
	//create 

		PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

		//update 

		PostDto updatePost(PostDto postDto, Integer postId);

		// delete

		void deletePost(Integer postId);
		
		//get all posts
		
	//	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
		
		
		 PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy ,String sortDir);
		
		//get single post
		
		PostDto getPostById(Integer postId);
		
		//get all posts by category
		
		List<PostDto> getPostsByCategory(Integer categoryId);
		
		//get all posts by user
		List<PostDto> getPostsByUser(Integer userId);
		
		//search posts
		List<PostDto> searchPosts(String keyword);

	
}

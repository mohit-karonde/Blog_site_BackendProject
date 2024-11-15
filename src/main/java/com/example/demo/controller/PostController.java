package com.example.demo.controller;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.configuration.AppConstants;
import com.example.demo.dto.PostDto;
import com.example.demo.payload.ApiResponse;
import com.example.demo.payload.PostResponse;
import com.example.demo.service.FileService;
import com.example.demo.service.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	PostService  postService;
	
	@Autowired
	FileService fileService;
	

	@Value("${project.image}")
	private String path;
	
	
	@PostMapping("/{userId}/{categoryId}")
	ResponseEntity<ApiResponse<PostDto>> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId, @PathVariable Integer categoryId){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("201","success","post creation succesfull",postService.createPost(postDto, userId, categoryId)));
		
	}
	
	@PutMapping("/{postId}")
	ResponseEntity<ApiResponse<PostDto>> updatePost(@RequestBody  PostDto postDto,@PathVariable Integer postId){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("201","Success","Post updated",postService.updatePost(postDto, postId)));
		
	}
	
	
	@GetMapping("/getPostBycategory/{categoryId}")
	ResponseEntity<ApiResponse<List<PostDto>>> getPostsByCategory( @PathVariable  Integer categoryId){
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse<>("202","success","gotPostByCategory",postService.getPostsByCategory(categoryId)));
	}
	
	
	@GetMapping("/getPostByUser/{userId}")
	ResponseEntity<ApiResponse<List<PostDto>>>  getPostsByUser( @PathVariable  Integer userId){
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse<>("202","success","gotPostByUser",postService.getPostsByUser(userId)));
	}

	@GetMapping("/getPostById/{postId}")
	ResponseEntity<ApiResponse<PostDto>>  getPostById( @PathVariable  Integer postId){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse<>("202","success","gotPostId",postService.getPostById(postId)));
	}

	
	@DeleteMapping("/{postId}")
	ResponseEntity<ApiResponse<String>>  deletePostById(@PathVariable Integer postId){
		
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("200","success","deleted","post deleted succesfully"));
	}
	
	@GetMapping("/getAllPost")
	ResponseEntity<ApiResponse<PostResponse>> getAllPost(
			@RequestParam(value ="pageNumber",defaultValue = AppConstants.PAGE_NUM,required = false) Integer PageNumber,
			@RequestParam(value ="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer PageSize,
			@RequestParam(value="sortedBy",defaultValue = AppConstants.SORT_BY,required = false) String SortedBy,
			@RequestParam(value="sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String SortDir){
		
		PostResponse postResponse = postService.getAllPost(PageNumber, PageSize ,SortedBy , SortDir);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("200","success","Got all post", postResponse));	
	}
	

	@GetMapping("/search/{keyword}")
	ResponseEntity<ApiResponse<List<PostDto>>> searchPosts(@PathVariable String keyword) {
		
		return  ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponse<>("302","successs","fetched all matching data" , postService.searchPosts(keyword)));
	}
	
	//postImageUploade
	@PostMapping("/upload/image/{postId}")
	ResponseEntity<ApiResponse<PostDto>> uploadImage(@RequestParam("Image") MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		
		PostDto postDto = postService.getPostById(postId);
		
		String fileName = fileService.uploadImage(path, image);
	
		postDto.setImageName(fileName);
		
		PostDto updatePostDto = postService.updatePost(postDto, postId);
		
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("200","success","image Uploaded successfully", updatePostDto));
		
		
		
	}
	
	  //method to serve files
	@GetMapping(value = "/post/image/{imageName}", produces = "image/jpeg")
	public void downloadImage(
	        @PathVariable String imageName,
	        HttpServletResponse response
	) throws IOException {
	    InputStream resource = fileService.getResource(path, imageName);
	    response.setContentType("image/jpeg");
	    StreamUtils.copy(resource, response.getOutputStream());
	    response.flushBuffer();
	}
	
	
	
	
	}


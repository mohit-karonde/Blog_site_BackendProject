package com.example.demo.serviceImplement;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PostDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.payload.PostResponse;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PostService;

@Service
public class PostServiceImplement implements PostService{
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
	       
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category not found", "Id nt found",categoryId));

		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("UserNotFound", "Useridproblem", userId));
		
		
		Post post = modelMapper.map(postDto, Post.class);
				post.setImageName("Default.png");
				
				post.setAddedDate(new Date());
				
				post.setUser(user);
				post.setCategory(category);
				
				Post savePost = postRepo.save(post);
		        return modelMapper.map(savePost,PostDto.class);
	
	}

	
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post existingPost = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("postId not found", "issue with id", postId));
		
	   // Post mappedPost = modelMapper.map(postDto, Post.class);
	 // update api me moddelMapper use krenga na to , post bn jayengi , better hai model mapper Dto return krne ke liye use
		//kro warna it will create issue
		
     //   Category category = categoryRepo.findById(postDto.getCategory().getCategoryId()).get();	
		existingPost.setTitle(postDto.getTitle());
		existingPost.setContent(postDto.getContent());
		existingPost.setImageName(postDto.getImageName());
		//existingPost.setCategory(category);
		Post updatedPost = postRepo.save(existingPost);
	        return modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		postRepo.deleteById(postId);
		
	}

	@Override
	public PostDto getPostById(Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("postId not found", "issue with id", postId));
		
         return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		
		   Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("CAtId not found", "issuewithId", categoryId));
		   List<Post>  post = postRepo.findByCategory(category);
		   
		   List<PostDto> postDto = post.stream().map(cat -> modelMapper.map(cat, PostDto.class)).collect(Collectors.toList());
		   
		   return postDto;
	
	
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("userId not found", "issue with id", userId));
		
		List<Post> post = postRepo.findByUser(user);
		
		List<PostDto> postDto = post.stream().map(cat -> modelMapper.map(cat, PostDto.class)).collect(Collectors.toList());
		
		return postDto;
		

	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
	List<Post>	posts = postRepo.findByTitleContaining(keyword);
	
	List<PostDto> postDto = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	   
	return postDto;
	}


	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy , String sortDir) {
	
		Sort sort =(sortDir.contentEquals("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		
        Page<Post> pagePost = postRepo.findAll(pageable);

        // Convert the Post entities to PostDto objects
        List<PostDto> postDtos = pagePost.getContent().stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        // Create and populate PostResponse
        
        PostResponse postResponse = new PostResponse();
        
        postResponse.setContent(postDtos);
        
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        
        postResponse.setLastPage(pagePost.isLast());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages() );
        
        
        
        return postResponse;
	}


	

}

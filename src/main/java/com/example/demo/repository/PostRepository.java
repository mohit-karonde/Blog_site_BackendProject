package com.example.demo.repository;

//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
//import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Category;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Integer>{
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByUser(User user);

	//Page<Post> findAll(Pageable pageable);
	
	List<Post> findByTitleContaining(String title);
	
	// alternate way to do it
}

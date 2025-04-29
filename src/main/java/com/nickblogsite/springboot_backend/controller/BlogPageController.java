package com.nickblogsite.springboot_backend.controller;

import java.util.*;

import org.springframework.web.bind.annotation.*;

import com.nickblogsite.springboot_backend.entity.*;
import com.nickblogsite.springboot_backend.repository.*;

import lombok.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class BlogPageController {

	private final BlogPageRepository blogPageRepository;

	@GetMapping("/blogPage")
	public List<BlogPage> fetchBlogs() {
		final List<BlogPage> blogPages = blogPageRepository.findAll();
		blogPages.sort( Comparator.comparingLong( BlogPage::getId ).reversed() );
		return blogPages;
	}

	@PostMapping("/addBlogPage")
	@ResponseBody
	public BlogPage addBlogPage( @RequestBody final BlogPage newBlogPage ) {
		return blogPageRepository.save( newBlogPage );
	}

	@PostMapping("/deleteBlogPage/{id}")
	public void deleteBlogPage( @PathVariable("id") final String id ) {
		blogPageRepository.deleteById( Long.parseLong( id ) );
	}

	@GetMapping("/getBlogPage/{id}")
	public BlogPage getBlogPage(@PathVariable("id") final String id ) {
		return blogPageRepository.findById( Long.parseLong( id ) ).orElse( null );
	}
}

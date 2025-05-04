package com.nickblogsite.springboot_backend.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import com.nickblogsite.springboot_backend.entity.*;

public interface BlogPageRepository extends JpaRepository<BlogPage, Long> {

	@Query("SELECT bp FROM BlogPage bp where bp.featured = true")
	List<BlogPage> getFeaturedBlogPages();

	@Query("SELECT bp FROM BlogPage bp where bp.featured = false")
	List<BlogPage> getUnFeaturedBlogPages();
}

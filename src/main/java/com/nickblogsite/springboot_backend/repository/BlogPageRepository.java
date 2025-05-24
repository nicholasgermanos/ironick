package com.nickblogsite.springboot_backend.repository;

import org.springframework.data.jpa.repository.*;
import java.util.*;

import com.nickblogsite.springboot_backend.entity.*;

public interface BlogPageRepository extends JpaRepository<BlogPage, Long> {

	@Query("SELECT bp FROM BlogPage bp where bp.published = true AND bp.featured = true")
	List<BlogPage> getFeaturedBlogPages();

	@Query("SELECT bp FROM BlogPage bp where bp.published = true AND bp.featured = false")
	List<BlogPage> getUnFeaturedBlogPages();
}

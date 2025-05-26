package com.nickblogsite.springboot_backend.repository;

import org.springframework.data.jpa.repository.*;
import java.util.*;

import com.nickblogsite.springboot_backend.entity.*;

public interface BlogPageRepository extends JpaRepository<BlogPage, Long> {

	@Query("SELECT bp FROM BlogPage bp where bp.published = true AND bp.featured = true")
	List<BlogPage> getFeaturedBlogPages();

	@Query("SELECT bp FROM BlogPage bp where bp.published = true AND bp.featured = false")
	List<BlogPage> getUnFeaturedBlogPages();

	@Query("SELECT bp FROM BlogPage bp WHERE bp.published = true")
	List<BlogPage> getPublishedBlogPages();

	@Query("SELECT bp FROM BlogPage bp where bp.published = false AND bp.user = ?1")
	List<BlogPage> getUnPublishedByUser( User user );

	@Query("SELECT bp FROM BlogPage bp where bp.published = true AND bp.user = ?1")
	List<BlogPage> getPublishedByUser( User user );

	@Query("SELECT bp FROM BlogPage bp where bp.isAboutPage = true")
	List<BlogPage> getAboutPage();

}

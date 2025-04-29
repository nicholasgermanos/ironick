package com.nickblogsite.springboot_backend.repository;

import org.springframework.data.jpa.repository.*;

import com.nickblogsite.springboot_backend.entity.*;

public interface BlogPageRepository extends JpaRepository<BlogPage, Long> {
}

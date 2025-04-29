package com.nickblogsite.springboot_backend.repository;

import org.springframework.data.jpa.repository.*;

import com.nickblogsite.springboot_backend.entity.*;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail( String username );
}

package com.nickblogsite.springboot_backend.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import com.nickblogsite.springboot_backend.entity.*;

public interface MailingListRepository extends JpaRepository<MailingList, Long> {
	List<MailingList> findByEmail( String email );
}

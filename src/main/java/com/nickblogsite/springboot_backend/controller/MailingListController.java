package com.nickblogsite.springboot_backend.controller;

import java.net.*;
import java.nio.charset.*;

import org.springframework.web.bind.annotation.*;

import com.nickblogsite.springboot_backend.entity.*;
import com.nickblogsite.springboot_backend.repository.*;

import lombok.*;

@RestController
@AllArgsConstructor
@RequestMapping( "/api" )
public class MailingListController {

	private final MailingListRepository mailingListRepository;

	@PostMapping( "registerEmail" )
	public void registerEmail( @RequestBody final String email ) {
		try {
			final MailingList mailingList = new MailingList();
			mailingList.setEmail( URLDecoder.decode( email, StandardCharsets.UTF_8 ) );
			mailingListRepository.save( mailingList );
		} catch ( final Exception e ) {
			throw new RuntimeException( e );
		}
	}
}
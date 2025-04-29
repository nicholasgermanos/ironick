package com.nickblogsite.springboot_backend.controller;

import java.util.*;

import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.web.bind.annotation.*;

import com.nickblogsite.springboot_backend.entity.*;
import com.nickblogsite.springboot_backend.enums.*;
import com.nickblogsite.springboot_backend.repository.*;

import lombok.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final AuthenticationProvider authenticationProvider;

	@GetMapping("/users")
	public List<User> fetchUsers() {
		return userRepository.findAll();
	}

	@PostMapping("/login")
	public ResponseEntity<Authentication> login( @RequestBody final User user ) {

		final Authentication authenticationRequest  = UsernamePasswordAuthenticationToken.unauthenticated( user.getEmail(), user.getPassword() );

		final Authentication authenticationResponse = authenticationProvider.authenticate( authenticationRequest );
		return ResponseEntity.status( HttpStatus.OK ).body( authenticationResponse );
	}

	@PostMapping("/register")
	public ResponseEntity<User> register( @RequestBody final User user ) {
		user.setRole( Roles.USER.name() );
		user.setPassword( bCryptPasswordEncoder.encode( user.getPassword() ) );
		return ResponseEntity.status( HttpStatus.CREATED ).body( userRepository.save( user ) );
	}
}

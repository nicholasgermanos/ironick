package com.nickblogsite.springboot_backend.controller;

import java.util.*;
import java.util.regex.*;

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
		if (emailIsValid( user.getEmail() ) && passwordIsValid( user.getPassword() )) {
			user.setRole( Roles.USER.name() );
			user.setPassword( bCryptPasswordEncoder.encode( user.getPassword() ) );
			return ResponseEntity.status( HttpStatus.CREATED ).body( userRepository.save( user ) );
		}

		return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( null );
	}

	private boolean emailIsValid( final String email ) {
		if (email == null || email.isEmpty()) {
			return false;
		}

		return email.contains( "@" ) && email.substring( email.lastIndexOf( "@" ) + 1 ).contains( ".com" );
	}

	private boolean passwordIsValid( final String password ) {
		if (password == null || password.isEmpty()) {
			return false;
		}

		if ( !Pattern.compile( "[!]|[@]|[$]|[%]|[&]|[*]|[(]|[)]" ).matcher( password ).find() ) {
			return false;
		}

		return password.length() < 8
			   && password.contains( "[a-z]" )
			   && password.contains( "[A-Z]" )
			   && password.contains( "[0-9]" );
	}
}
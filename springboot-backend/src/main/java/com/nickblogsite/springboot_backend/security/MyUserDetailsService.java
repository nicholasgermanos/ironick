package com.nickblogsite.springboot_backend.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import com.nickblogsite.springboot_backend.entity.User;
import com.nickblogsite.springboot_backend.repository.*;

import lombok.*;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername( final String username ) throws UsernameNotFoundException {
		final User user = userRepository.findByEmail( username );

		if (user == null) {
			throw new UsernameNotFoundException( "Incorrect username or password" );
		}

		return new MyUserDetails( user );
	}
}

package com.nickblogsite.springboot_backend.security;

import java.util.*;

import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;

import com.nickblogsite.springboot_backend.entity.User;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class MyUserDetails implements UserDetails {
	private User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of( new SimpleGrantedAuthority( user.getRole() ) );
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return UserDetails.super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return UserDetails.super.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return UserDetails.super.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return UserDetails.super.isEnabled();
	}
}

package com.nickblogsite.springboot_backend.configuration;

import java.util.*;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

import lombok.*;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private static final String ANY_MATCHER = "{path:(?!.*\\.).*}";

	@Override
	public void addResourceHandlers( final ResourceHandlerRegistry registry ) {

		registry.addResourceHandler( "/dist/**" )
			.addResourceLocations( "/dist/" );
	}

	@Override
	public void addViewControllers( @NonNull final ViewControllerRegistry registry ) {

		final List<String> validPaths = new ArrayList<>();
		validPaths.add("/");
		validPaths.add("/" + ANY_MATCHER);
		validPaths.add("/blog/" + ANY_MATCHER );
		validPaths.add("/newEntryForm/" + ANY_MATCHER );

		for (final String path : validPaths) {
			registry.addViewController( path )
				.setViewName( "forward:/dist/index.html" );
		}
	}

}

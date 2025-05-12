package com.nickblogsite.springboot_backend.configuration;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers( final ResourceHandlerRegistry registry ) {

		registry.addResourceHandler( "/dist/**" )
			.addResourceLocations( "/dist/" );
	}

	@Override
	public void addViewControllers( final ViewControllerRegistry registry ) {

		registry.addViewController( "/" )
			.setViewName( "forward:/dist/index.html" );
	}

}

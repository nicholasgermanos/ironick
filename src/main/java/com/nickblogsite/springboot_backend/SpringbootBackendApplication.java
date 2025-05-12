package com.nickblogsite.springboot_backend;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.builder.*;
import org.springframework.boot.web.servlet.support.*;

@SpringBootApplication
public class SpringbootBackendApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure( final SpringApplicationBuilder application ) {
		return application.sources(SpringbootBackendApplication.class);
	}

	public static void main( final String[] args ) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}
}

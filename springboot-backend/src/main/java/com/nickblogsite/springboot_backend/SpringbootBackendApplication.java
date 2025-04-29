package com.nickblogsite.springboot_backend;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nickblogsite.springboot_backend.entity.*;
import com.nickblogsite.springboot_backend.repository.*;

@SpringBootApplication
public class SpringbootBackendApplication {

	public static void main( final String[] args ) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}
}

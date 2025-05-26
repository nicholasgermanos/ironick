package com.nickblogsite.springboot_backend.controller;

import java.io.*;
import java.util.*;
import java.util.logging.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import com.nickblogsite.springboot_backend.entity.*;
import com.nickblogsite.springboot_backend.repository.*;

import lombok.*;
import lombok.extern.java.*;

@Log
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CoverImageController {

	private final CoverImageRepository coverImageRepository;
	private final BlogPageRepository blogPageRepository;

	@PostMapping("/addCoverImage/{id}")
	public void addCoverImage( @PathVariable("id") final String id, @RequestParam("image") final MultipartFile file ) {

		final CoverImage coverImage = coverImageRepository.findById( Long.parseLong( id ) ).orElse( new CoverImage() );

		try {
			coverImage.setBlogPage( blogPageRepository.findById( Long.parseLong( id ) ).orElse( null ) );
			coverImage.setData( file.getBytes() );

			coverImageRepository.save( coverImage );
		} catch ( final IOException ioException ) {
			log.log( Level.SEVERE, ioException.getMessage(), ioException );
		}
	}
}

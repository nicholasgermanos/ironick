package com.nickblogsite.springboot_backend.entity;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="cover_image")
public class CoverImage {
	@Id
	@Column(name = "blog_page_id")
	private long id;

	@Lob
	private byte[] data;

	@JsonBackReference
	@OneToOne
	@MapsId
	@JoinColumn(name = "cover_image_id")
	private BlogPage blogPage;
}

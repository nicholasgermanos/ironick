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
@Table(name="blog_page")
public class BlogPage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long timestamp;
	private String title;
	private String subtitle;
	private boolean featured;
	private boolean published;

	@Column(length = 100000)
	private String body;

	@JsonManagedReference
	@OneToOne(mappedBy = "blogPage", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private CoverImage coverImage;

	@ManyToOne
	@JsonIgnoreProperties("blogPages")
	@JoinColumn(name="user_id", nullable = false)
	private User user;
}

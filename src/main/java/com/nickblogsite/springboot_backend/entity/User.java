package com.nickblogsite.springboot_backend.entity;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(unique = true, nullable = false)
	private String email;
	private String password;
	private String role;

	@OneToMany(mappedBy = "user")
	@JsonBackReference
	private Set<BlogPage> blogPages;
}

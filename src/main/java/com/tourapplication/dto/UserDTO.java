package com.tourapplication.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDTO {
	private Long id;

	@NotBlank(message = "Name is required")
	private String name;

	@Email(message = "Email should be valid")
	private String email;

	public UserDTO() {
		super();
	}

	public UserDTO(Long id, @NotBlank(message = "Name is required") String name,
			@Email(message = "Email should be valid") String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
}

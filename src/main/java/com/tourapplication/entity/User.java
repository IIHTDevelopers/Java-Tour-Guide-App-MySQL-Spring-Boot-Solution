package com.tourapplication.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name is required")
	private String name;

	@Email(message = "Email should be valid")
	private String email;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<TourPackage> tourPackages;

	public User() {
		super();
	}

	public User(Long id, @NotBlank(message = "Name is required") String name,
			@Email(message = "Email should be valid") String email, List<TourPackage> tourPackages) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.tourPackages = tourPackages;
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

	public List<TourPackage> getTourPackages() {
		return tourPackages;
	}

	public void setTourPackages(List<TourPackage> tourPackages) {
		this.tourPackages = tourPackages;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", tourPackages=" + tourPackages + "]";
	}
}

package com.tourapplication.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class TourPackage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Place name is required")
	private String placeName;

	private int numberOfDays;

	private double price;

	@NotBlank(message = "Places to visit should be provided")
	private String placesToVisit;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public TourPackage() {
		super();
	}

	public TourPackage(Long id, @NotBlank(message = "Place name is required") String placeName, int numberOfDays,
			double price, @NotBlank(message = "Places to visit should be provided") String placesToVisit, User user) {
		super();
		this.id = id;
		this.placeName = placeName;
		this.numberOfDays = numberOfDays;
		this.price = price;
		this.placesToVisit = placesToVisit;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPlacesToVisit() {
		return placesToVisit;
	}

	public void setPlacesToVisit(String placesToVisit) {
		this.placesToVisit = placesToVisit;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "TourPackage [id=" + id + ", placeName=" + placeName + ", numberOfDays=" + numberOfDays + ", price="
				+ price + ", placesToVisit=" + placesToVisit + ", user=" + user + "]";
	}
}

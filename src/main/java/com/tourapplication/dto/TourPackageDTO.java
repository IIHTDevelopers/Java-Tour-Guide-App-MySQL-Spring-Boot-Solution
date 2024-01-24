package com.tourapplication.dto;

import com.tourapplication.entity.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class TourPackageDTO {
	private Long id;

	@NotBlank(message = "Place name is required")
	private String placeName;

	@Positive
	private int numberOfDays;

	@Positive
	private double price;

	private User user;

	@NotBlank(message = "Places to visit should be provided")
	private String placesToVisit;

	public TourPackageDTO() {
		super();
	}

	public TourPackageDTO(Long id, @NotBlank(message = "Place name is required") String placeName, int numberOfDays,
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
		return "TourPackageDTO [id=" + id + ", placeName=" + placeName + ", numberOfDays=" + numberOfDays + ", price="
				+ price + ", user=" + user + ", placesToVisit=" + placesToVisit + "]";
	}
}

package com.tourapplication.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

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

	public TourPackage() {
		super();
	}

	public TourPackage(Long id, @NotBlank(message = "Place name is required") String placeName, int numberOfDays,
			double price, @NotBlank(message = "Places to visit should be provided") String placesToVisit) {
		super();
		this.id = id;
		this.placeName = placeName;
		this.numberOfDays = numberOfDays;
		this.price = price;
		this.placesToVisit = placesToVisit;
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

	@Override
	public String toString() {
		return "Package [id=" + id + ", placeName=" + placeName + ", numberOfDays=" + numberOfDays + ", price=" + price
				+ ", placesToVisit=" + placesToVisit + "]";
	}
}

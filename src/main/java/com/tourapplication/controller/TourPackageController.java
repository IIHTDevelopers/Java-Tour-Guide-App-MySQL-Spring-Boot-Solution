package com.tourapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tourapplication.dto.TourPackageDTO;
import com.tourapplication.service.TourPackageService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/api/packages")
public class TourPackageController {

	private final TourPackageService packageService;

	@Autowired
	public TourPackageController(TourPackageService packageService) {
		this.packageService = packageService;
	}

	@GetMapping
	public ResponseEntity<List<TourPackageDTO>> getAllPackages() {
		List<TourPackageDTO> packages = packageService.getAllPackages();
		return new ResponseEntity<>(packages, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TourPackageDTO> getPackageById(@PathVariable Long id) {
		TourPackageDTO tourPackageDTO;
		try {
			tourPackageDTO = packageService.getPackageById(id);
			return new ResponseEntity<>(tourPackageDTO, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<TourPackageDTO> createPackage(@RequestBody @Valid TourPackageDTO tourPackageDTO) {
		TourPackageDTO createdPackage = packageService.createPackage(tourPackageDTO);
		return new ResponseEntity<>(createdPackage, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TourPackageDTO> updatePackage(@PathVariable Long id,
			@RequestBody @Valid TourPackageDTO tourPackageDTO) {
		TourPackageDTO updatedPackage;
		try {
			updatedPackage = packageService.updatePackage(id, tourPackageDTO);
			return new ResponseEntity<>(updatedPackage, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePackageById(@PathVariable Long id) {
		try {
			packageService.deletePackageById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

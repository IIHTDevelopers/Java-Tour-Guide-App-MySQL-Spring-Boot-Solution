package com.tourapplication.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tourapplication.dto.TourPackageDTO;
import com.tourapplication.exception.NotFoundException;

public interface TourPackageService {
	TourPackageDTO getPackageById(Long packageId) throws NotFoundException;

	TourPackageDTO createPackage(TourPackageDTO tourPackageDTO);

	TourPackageDTO updatePackage(Long packageId, TourPackageDTO tourPackageDTO) throws NotFoundException;

	boolean deletePackageById(Long packageId) throws NotFoundException;

	Page<TourPackageDTO> getAllPackages(Pageable pageable);

	List<TourPackageDTO> getAllPackagesByNumberOfDays(int numberOfDays);

	List<TourPackageDTO> getAllPackagesByUser(Long userId);
}

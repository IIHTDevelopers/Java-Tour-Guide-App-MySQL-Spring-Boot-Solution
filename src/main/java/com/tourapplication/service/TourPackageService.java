package com.tourapplication.service;

import java.util.List;

import com.tourapplication.dto.TourPackageDTO;

import javassist.NotFoundException;

public interface TourPackageService {
	List<TourPackageDTO> getAllPackages();

	TourPackageDTO getPackageById(Long packageId) throws NotFoundException;

	TourPackageDTO createPackage(TourPackageDTO tourPackageDTO);

	TourPackageDTO updatePackage(Long packageId, TourPackageDTO tourPackageDTO) throws NotFoundException;

	boolean deletePackageById(Long packageId) throws NotFoundException;
}

package com.tourapplication.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tourapplication.dto.TourPackageDTO;
import com.tourapplication.entity.TourPackage;
import com.tourapplication.repo.TourPackageRepository;
import com.tourapplication.service.TourPackageService;

import javassist.NotFoundException;

@Service
public class TourPackageServiceImpl implements TourPackageService {

	private final TourPackageRepository tourPackageRepository;

	@Autowired
	public TourPackageServiceImpl(TourPackageRepository tourPackageRepository) {
		this.tourPackageRepository = tourPackageRepository;
	}

	@Override
	public Page<TourPackageDTO> getAllPackages(Pageable pageable) {
		Page<TourPackage> packages = tourPackageRepository.findAll(pageable);
		return packages.map(this::convertToDTO);
	}

	@Override
	public List<TourPackageDTO> getAllPackagesByNumberOfDays(int numberOfDays) {
		List<TourPackage> packages = tourPackageRepository.findByNumberOfDays(numberOfDays);
		return packages.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public List<TourPackageDTO> getAllPackagesByUser(Long userId) {
		List<TourPackage> packages = tourPackageRepository.findByUserId(userId);
		return packages.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public TourPackageDTO getPackageById(Long packageId) throws NotFoundException {
		TourPackage tourPackage = getTourPackageById(packageId);
		return convertToDTO(tourPackage);
	}

	@Override
	public TourPackageDTO createPackage(TourPackageDTO tourPackageDTO) {
		TourPackage tourPackage = convertToEntity(tourPackageDTO);
		return convertToDTO(tourPackageRepository.save(tourPackage));
	}

	@Override
	public TourPackageDTO updatePackage(Long packageId, TourPackageDTO tourPackageDTO) throws NotFoundException {
		TourPackage existingTourPackage = getTourPackageById(packageId);
		BeanUtils.copyProperties(tourPackageDTO, existingTourPackage);
		return convertToDTO(tourPackageRepository.save(existingTourPackage));
	}

	@Override
	public boolean deletePackageById(Long packageId) throws NotFoundException {
		TourPackage tourPackage = getTourPackageById(packageId);
		tourPackageRepository.delete(tourPackage);
		return true;
	}

	private TourPackageDTO convertToDTO(TourPackage tourPackage) {
		TourPackageDTO tourPackageDTO = new TourPackageDTO();
		BeanUtils.copyProperties(tourPackage, tourPackageDTO);
		return tourPackageDTO;
	}

	private TourPackage convertToEntity(TourPackageDTO tourPackageDTO) {
		TourPackage tourPackage = new TourPackage();
		BeanUtils.copyProperties(tourPackageDTO, tourPackage);
		return tourPackage;
	}

	private TourPackage getTourPackageById(Long packageId) throws NotFoundException {
		return tourPackageRepository.findById(packageId)
				.orElseThrow(() -> new NotFoundException("TourPackage not found"));
	}
}

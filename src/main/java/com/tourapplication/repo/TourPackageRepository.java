package com.tourapplication.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tourapplication.entity.TourPackage;

@Repository
public interface TourPackageRepository extends JpaRepository<TourPackage, Long> {

	List<TourPackage> findByNumberOfDays(int numberOfDays);

	List<TourPackage> findByUserId(Long userId);
}

package com.tourapplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tourapplication.entity.TourPackage;

@Repository
public interface TourPackageRepository extends JpaRepository<TourPackage, Long> {
}

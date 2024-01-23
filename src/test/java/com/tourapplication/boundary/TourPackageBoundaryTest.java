package com.tourapplication.boundary;

import static com.tourapplication.utils.TestUtils.boundaryTestFile;
import static com.tourapplication.utils.TestUtils.currentTest;
import static com.tourapplication.utils.TestUtils.testReport;
import static com.tourapplication.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.tourapplication.dto.TourPackageDTO;

public class TourPackageBoundaryTest {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testPlaceNameNotNull() throws IOException {
		TourPackageDTO tourPackageDTO = new TourPackageDTO();
		tourPackageDTO.setPlaceName(null);
		Set<ConstraintViolation<TourPackageDTO>> violations = validator.validate(tourPackageDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testPlacesToVisitNotNull() throws IOException {
		TourPackageDTO tourPackageDTO = new TourPackageDTO();
		tourPackageDTO.setPlacesToVisit(null);
		Set<ConstraintViolation<TourPackageDTO>> violations = validator.validate(tourPackageDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testNumberOfDaysPositive() throws IOException {
		TourPackageDTO tourPackageDTO = new TourPackageDTO();
		tourPackageDTO.setNumberOfDays(-1);
		Set<ConstraintViolation<TourPackageDTO>> violations = validator.validate(tourPackageDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testPricePositive() throws IOException {
		TourPackageDTO tourPackageDTO = new TourPackageDTO();
		tourPackageDTO.setPrice(-1);
		Set<ConstraintViolation<TourPackageDTO>> violations = validator.validate(tourPackageDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testUserNotNull() throws IOException {
		TourPackageDTO tourPackageDTO = new TourPackageDTO();
		tourPackageDTO.setUser(null);
		Set<ConstraintViolation<TourPackageDTO>> violations = validator.validate(tourPackageDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}

package com.tourapplication.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tourapplication.dto.TourPackageDTO;
import com.tourapplication.dto.UserDTO;

public class MasterData {

	public static UserDTO getUserDTO() {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);
		userDTO.setName("John Doe");
		userDTO.setEmail("john@example.com");
		return userDTO;
	}

	public static List<UserDTO> getUserDTOList() {
		List<UserDTO> userDTOList = new ArrayList<>();
		userDTOList.add(getUserDTO());
		return userDTOList;
	}

	public static TourPackageDTO getTourPackageDTO() {
		TourPackageDTO tourPackageDTO = new TourPackageDTO();
		tourPackageDTO.setId(1L);
		tourPackageDTO.setPlaceName("Example Place");
		tourPackageDTO.setNumberOfDays(5);
		tourPackageDTO.setPrice(1000.0);
		tourPackageDTO.setPlacesToVisit("Attraction A, Attraction B, Attraction C");
		return tourPackageDTO;
	}

	public static List<TourPackageDTO> getTourPackageDTOList() {
		List<TourPackageDTO> tourPackageDTOList = new ArrayList<>();
		tourPackageDTOList.add(getTourPackageDTO());
		return tourPackageDTOList;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

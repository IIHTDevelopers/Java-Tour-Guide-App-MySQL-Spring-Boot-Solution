package com.tourapplication.functional;

import static com.tourapplication.utils.MasterData.getTourPackageDTO;
import static com.tourapplication.utils.TestUtils.businessTestFile;
import static com.tourapplication.utils.TestUtils.currentTest;
import static com.tourapplication.utils.TestUtils.testReport;
import static com.tourapplication.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.tourapplication.controller.TourPackageController;
import com.tourapplication.dto.TourPackageDTO;
import com.tourapplication.service.TourPackageService;
import com.tourapplication.utils.MasterData;

@WebMvcTest(TourPackageController.class)
@AutoConfigureMockMvc
public class TourPackageControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TourPackageService packageService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetPackageById() throws Exception {
		TourPackageDTO tourPackageDTO = getTourPackageDTO();
		when(this.packageService.getPackageById(tourPackageDTO.getId())).thenReturn(tourPackageDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/packages/" + tourPackageDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(tourPackageDTO))
						? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testCreatePackage() throws Exception {
		TourPackageDTO tourPackageDTO = getTourPackageDTO();

		when(this.packageService.createPackage(any())).thenReturn(tourPackageDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/packages")
				.content(MasterData.asJsonString(tourPackageDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(tourPackageDTO))
						? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testUpdatePackage() throws Exception {
		TourPackageDTO tourPackageDTO = getTourPackageDTO();

		when(this.packageService.updatePackage(eq(tourPackageDTO.getId()), any())).thenReturn(tourPackageDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/packages/" + tourPackageDTO.getId())
				.content(MasterData.asJsonString(tourPackageDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(tourPackageDTO))
						? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testDeletePackage() throws Exception {
		TourPackageDTO tourPackageDTO = getTourPackageDTO();
		when(this.packageService.deletePackageById(tourPackageDTO.getId())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/packages/" + tourPackageDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals("") ? "true" : "false"),
				businessTestFile);
	}

	@Test
	public void testGetAllPackagesByNumberOfDaysValid() throws Exception {
		int numberOfDays = 3;

		when(this.packageService.getAllPackagesByNumberOfDays(numberOfDays)).thenReturn(List.of(getTourPackageDTO()));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/packages/by-days")
				.param("numberOfDays", String.valueOf(numberOfDays)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value()
				&& result.getResponse().getContentAsString().contains("placeName")), businessTestFile);
	}

	@Test
	public void testGetAllPackagesByUserValid() throws Exception {
		Long userId = 1L;

		when(this.packageService.getAllPackagesByUser(userId)).thenReturn(List.of(getTourPackageDTO()));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/packages/by-user/" + userId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), (result.getResponse().getStatus() == HttpStatus.OK.value()
				&& result.getResponse().getContentAsString().contains("placeName")), businessTestFile);
	}
}
package com.tourapplication.exception;

import static com.tourapplication.utils.MasterData.getTourPackageDTO;
import static com.tourapplication.utils.TestUtils.currentTest;
import static com.tourapplication.utils.TestUtils.exceptionTestFile;
import static com.tourapplication.utils.TestUtils.testReport;
import static com.tourapplication.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

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
public class TourPackageExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TourPackageService packageService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreatePackageInvalidDataException() throws Exception {
		TourPackageDTO tourPackageDTO = getTourPackageDTO();
		tourPackageDTO.setPlaceName(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/packages")
				.content(MasterData.asJsonString(tourPackageDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdatePackageInvalidDataException() throws Exception {
		TourPackageDTO tourPackageDTO = getTourPackageDTO();
		tourPackageDTO.setPlaceName(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/packages/" + tourPackageDTO.getId())
				.content(MasterData.asJsonString(tourPackageDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testGetPackageByIdNotFoundException() throws Exception {
		TourPackageDTO tourPackageDTO = getTourPackageDTO();

		when(this.packageService.getPackageById(tourPackageDTO.getId()))
				.thenThrow(new NotFoundException("Package not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/packages/" + tourPackageDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.NOT_FOUND.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testDeletePackageByIdNotFoundException() throws Exception {
		TourPackageDTO tourPackageDTO = getTourPackageDTO();

		when(this.packageService.deletePackageById(tourPackageDTO.getId()))
				.thenThrow(new NotFoundException("Package not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/packages/" + tourPackageDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.NOT_FOUND.value() ? "true" : "false"),
				exceptionTestFile);
	}
}

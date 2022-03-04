package com.te.carinfoapp.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.carinfoapp.dto.CarDetails;
import com.te.carinfoapp.dto.CarDetailsResponse;
import com.te.carinfoapp.dto.UserResponse;
import com.te.carinfoapp.service.UserService;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserControllerTest {
	@MockBean
	private UserService userService;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	private ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void testGetAllCarInfo() throws UnsupportedEncodingException, Exception {
		CarDetails carDetails=new CarDetails();
		carDetails.setId(1);
		carDetails.setName("BMW 650D");
		carDetails.setCompany("BMW");
		carDetails.setFuelType("Diesel");
		carDetails.setPowerSteering(true);
		carDetails.setBreakSystem("ABS");
		carDetails.setShowroomPrice(2000000d);
		carDetails.setImageURL("https//sjdfsjakeurgdheahgh");
		carDetails.setMileage(20d);
		carDetails.setSeatingCapacity(4);
		carDetails.setEngineCapacity(1000);
		carDetails.setGearType("Automatic");
		List<CarDetails> listOfCarDetails=new ArrayList<CarDetails>();
		
		listOfCarDetails.add(carDetails);
		when(userService.getAllCarInfo()).thenReturn(listOfCarDetails);
		
		String contentAsString = mockMvc.perform(get("/user/car/info").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
		.andReturn().getResponse().getContentAsString();
		
		UserResponse readValue = mapper.readValue(contentAsString, UserResponse.class);
		assertNotNull(readValue.getAllCarDetails());
	}

	@Test
	void testSearchCarInfo() throws UnsupportedEncodingException, Exception {
		
		MockHttpServletRequest httpServletRequest=new MockHttpServletRequest();
		httpServletRequest.setParameter("search", "BMW");
		
		
		CarDetails carDetails=new CarDetails();
		carDetails.setId(1);
		carDetails.setName("BMW 650D");
		carDetails.setCompany("BMW");
		carDetails.setFuelType("Diesel");
		carDetails.setPowerSteering(true);
		carDetails.setBreakSystem("ABS");
		carDetails.setShowroomPrice(2000000d);
		carDetails.setImageURL("https//sjdfsjakeurgdheahgh");
		carDetails.setMileage(20d);
		carDetails.setSeatingCapacity(4);
		carDetails.setEngineCapacity(1000);
		carDetails.setGearType("Automatic");
		List<CarDetails> listOfCarDetails=new ArrayList<CarDetails>();
		
		listOfCarDetails.add(carDetails);
		when(userService.SearchCarInfo("BMW")).thenReturn(listOfCarDetails);
		
		String contentAsString = mockMvc.perform(get("/user/car/search").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
		.andReturn().getResponse().getContentAsString();
		
		UserResponse readValue = mapper.readValue(contentAsString, UserResponse.class);
		assertNotNull(readValue.getSearchCarDetails());
	}

}

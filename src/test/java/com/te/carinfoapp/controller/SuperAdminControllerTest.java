package com.te.carinfoapp.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.mapper.Mapper;
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
import com.te.carinfoapp.dto.SuperAdminResponse;
import com.te.carinfoapp.service.SuperAdminService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SuperAdminControllerTest {
	@MockBean
	private SuperAdminService superAdminService;
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext applicationContext;
	
	private ObjectMapper mapper=new ObjectMapper();
	
	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
	}
	
	
	@Test
	void getAllCarDetailsWithAdminDetailsTest() throws UnsupportedEncodingException, Exception {
		MockHttpServletRequest httpServletRequest=new MockHttpServletRequest();
		httpServletRequest.addHeader("Authorization", "Bearer nbnbvbnncvnchhc");
		
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
		when(superAdminService.getAllCarDetails()).thenReturn(listOfCarDetails);
		
		String contentAsString = mockMvc.perform(get("/superAdmin/car/info").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
		.andReturn().getResponse().getContentAsString();
		
		SuperAdminResponse readValue = mapper.readValue(contentAsString, SuperAdminResponse.class);
		assertNotNull(readValue.getCarDetailsWithAdminName());
		
	}

}

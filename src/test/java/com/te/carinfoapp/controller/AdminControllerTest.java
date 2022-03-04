package com.te.carinfoapp.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.carinfoapp.dto.AdminDetails;
import com.te.carinfoapp.dto.AdminRequest;
import com.te.carinfoapp.dto.AdminResponse;
import com.te.carinfoapp.dto.CarDetails;
import com.te.carinfoapp.dto.CarDetailsResponse;
import com.te.carinfoapp.dto.MyAdminDetails;
import com.te.carinfoapp.service.AdminService;
import com.te.carinfoapp.util.JwtUtil;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AdminControllerTest {

	@MockBean
	private AuthenticationManager authenticationManager;

	@MockBean
	private UserDetailsService userDetailsService;

	@MockBean
	private JwtUtil jwtUtil;

	@MockBean
	private AdminService adminService;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	private ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void testCreateLoginAuthenticationToken() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		AdminRequest adminRequest=new AdminRequest("user", "1234");
		AdminDetails admin=new AdminDetails();
		admin.setId(1);
		admin.setUsername("user");
		admin.setPassword("1234");
		admin.setRole("ROLE_ADMIN");
		MyAdminDetails adminDetails=new MyAdminDetails(admin );
		when(adminService.checkIfUsernameExists("user")).thenReturn(true);
		when(authenticationManager.authenticate(Mockito.any())).thenReturn(null);
		when(userDetailsService.loadUserByUsername("user")).thenReturn(adminDetails);
		when(adminService.adminDetails("user")).thenReturn(admin);
		when(jwtUtil.generateToken(Mockito.any())).thenReturn("nbnbvbnncvnchhc");
		
		
		String contentAsString = mockMvc.perform(post("/admin/loginAuthentication").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(adminRequest)).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn()
		.getResponse().getContentAsString();
		
		AdminResponse readValue = mapper.readValue(contentAsString, AdminResponse.class);
		assertEquals("Authentication Success", readValue.getMessage());
		
		
	}

	@Test
	void testCreateSignupAuthenticationToken() throws JsonProcessingException, Exception {
		AdminDetails admin=new AdminDetails();
		admin.setId(1);
		admin.setUsername("Surya12");
		admin.setPassword("Surya@12");
		admin.setRole("ROLE_ADMIN");
		when(adminService.saveSignupData(admin)).thenReturn(admin);
		when(authenticationManager.authenticate(Mockito.any())).thenReturn(null);
		MyAdminDetails adminDetails=new MyAdminDetails(admin );
		when(userDetailsService.loadUserByUsername("user")).thenReturn(adminDetails);
		when(adminService.adminDetails("user")).thenReturn(admin);
		when(jwtUtil.generateToken(Mockito.any())).thenReturn("nbnbvbnncvnchhc");
		
		String contentAsString = mockMvc.perform(post("/admin/signupAuthentication").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writeValueAsString(admin))
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		AdminResponse readValue = mapper.readValue( contentAsString,AdminResponse.class);
		assertEquals("nbnbvbnncvnchhc", readValue.getToken());
		
	}
	
	
	
	
	
	
	

	@Test
	void testGetAllCarDetails() throws UnsupportedEncodingException, Exception {
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
		when(adminService.getAllCarDetails(httpServletRequest)).thenReturn(listOfCarDetails);
		
		String contentAsString = mockMvc.perform(get("/admin/car/info").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
		.andReturn().getResponse().getContentAsString();
		
		CarDetailsResponse readValue = mapper.readValue(contentAsString, CarDetailsResponse.class);
		assertNotNull(readValue.getAllCarDetails());

		
		
	}

	@Test
	void testAddCarDetails() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		MockHttpServletRequest request=new MockHttpServletRequest();
		request.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJOYWdlc2gxMSIsImV4cCI6MTY0NjM2MDY2OCwiaWF0IjoxNjQ2MzI0NjY4fQ.dBLDnBHyMPJL7K5KzifmL2GJEJrr9ZZWi3amXaktGls");
		
		
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
		
		when(adminService.addCarDetails(carDetails, request)).thenReturn(carDetails);
		
		String contentAsString = mockMvc.perform(post("/admin/car/info").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(carDetails)).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
		.andReturn().getResponse().getContentAsString();
		
		CarDetailsResponse readValue = mapper.readValue(contentAsString, CarDetailsResponse.class);
		assertEquals("Car Details Added Successfully",readValue.getMessage());
		
	}

	@Test
	void testUpdateCarDetails() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		MockHttpServletRequest request=new MockHttpServletRequest();
		request.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJOYWdlc2gxMSIsImV4cCI6MTY0NjM2MDY2OCwiaWF0IjoxNjQ2MzI0NjY4fQ.dBLDnBHyMPJL7K5KzifmL2GJEJrr9ZZWi3amXaktGls");

		
		CarDetails carDetails=new CarDetails();

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
		carDetails.setId(5);
		
		when(adminService.updateCarDetails(carDetails, request,5)).thenReturn(carDetails);
		
		String contentAsString = mockMvc.perform(put("/admin/car/info/5").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(carDetails)).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
		.andReturn().getResponse().getContentAsString();
		
		
		
		CarDetailsResponse readValue = mapper.readValue(contentAsString, CarDetailsResponse.class);
		System.out.println("result"+contentAsString);
		assertEquals("Car Details Updated Successfully",readValue.getMessage());
		
	}

	@Test
	void testDeleteCarDetails() throws UnsupportedEncodingException, Exception {
		when(adminService.deleteCarDetails(2)).thenReturn(true);
		
		String contentAsString = mockMvc.perform(delete("/admin/car/info/2").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		CarDetailsResponse readValue = mapper.readValue(contentAsString, CarDetailsResponse.class);
		assertEquals("Car Details Deleted Successfully", readValue.getMessage());
	}

}

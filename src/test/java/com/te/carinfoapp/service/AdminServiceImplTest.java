package com.te.carinfoapp.service;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;

import com.te.carinfoapp.dao.AdminDao;
import com.te.carinfoapp.dao.CarDetailsDao;
import com.te.carinfoapp.dto.AdminDetails;
import com.te.carinfoapp.dto.CarDetails;
import com.te.carinfoapp.util.JwtUtil;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {

	@InjectMocks
	private AdminServiceImpl adminServiceImpl;

	@Mock
	private AdminDao adminDao;

	@Mock
	private CarDetailsDao carDao;

	@Mock
	private JwtUtil jwtUtil;

	@Test
	void testLoadUserByUsername() {
		AdminDetails admin = new AdminDetails();
		admin.setId(1);
		admin.setUsername("user");
		admin.setPassword("1234");
		admin.setRole("ROLE_ADMIN");

		when(adminDao.findByUsername("user")).thenReturn(admin);
		UserDetails loadUserByUsername = adminServiceImpl.loadUserByUsername("user");

		assertEquals("user", loadUserByUsername.getUsername());

	}

	@Test
	void testSaveSignupData() {

		AdminDetails admin = new AdminDetails();
		admin.setId(1);

		admin.setUsername("Surya12");
		admin.setPassword("Surya@12");
		admin.setRole("ROLE_ADMIN");

		when(adminDao.save(Mockito.any())).thenReturn(admin);
		AdminDetails saveSignupData = adminServiceImpl.saveSignupData(admin);
		assertEquals("ROLE_ADMIN", saveSignupData.getRole());

	}

	@Test
	void testGetAllCarDetails() {

		MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
		httpServletRequest.addHeader("Authorization", "Bearer nbnbvbnncvnchhc");

		CarDetails carDetails = new CarDetails();
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
		carDetails.setAdminName("Nagesh11");

		List<CarDetails> details = new ArrayList<CarDetails>();
		details.add(carDetails);
		when(jwtUtil.extractUsername(Mockito.anyString())).thenReturn("Nagesh11");
		when(carDao.findByAdminName(carDetails.getAdminName())).thenReturn(details);

		List<CarDetails> details2 = adminServiceImpl.getAllCarDetails(httpServletRequest);

		assertEquals("Automatic", details2.get(0).getGearType());
	}

	@Test
	void testAddCarDetails() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Authorization", "Bearer fgdhakahceghhgfsidfhh");

		CarDetails carDetails = new CarDetails();
		carDetails.setId(2);
		carDetails.setName("BMW 650D");
		carDetails.setCompany("BMW");
		carDetails.setFuelType("Electric");
		carDetails.setPowerSteering(true);
		carDetails.setBreakSystem("ABS");
		carDetails.setShowroomPrice(2000000d);
		carDetails.setImageURL("https//sjdfsjakeurgdheahgh");
		carDetails.setMileage(20d);
		carDetails.setSeatingCapacity(4);
		carDetails.setEngineCapacity(1000);
		carDetails.setGearType("Automatic");

		AdminDetails admin = new AdminDetails();
		admin.setId(1);
		admin.setUsername("Surya12");
		admin.setPassword("Surya@12");
		admin.setRole("ROLE_ADMIN");

		when(carDao.save(carDetails)).thenReturn(carDetails);

		CarDetails addCarDetails = adminServiceImpl.addCarDetails(carDetails, request);

		assertEquals(2080000.00, addCarDetails.getOnroadPrice());

	}

	@Test
	void testUpdateCarDetails() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("Authorization", "Bearer fgdhakahceghhgfsidfhh");

		int carId = 2;

		CarDetails carDetails = new CarDetails();
		carDetails.setName("BMW 655D");
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

		AdminDetails admin = new AdminDetails();
		admin.setId(1);
		admin.setUsername("Surya12");
		admin.setPassword("Surya@12");
		admin.setRole("ROLE_ADMIN");

		carDetails.setAdminName(admin.getUsername());
		carDetails.setId(carId);

		when(carDao.save(carDetails)).thenReturn(carDetails);

		CarDetails addCarDetails = adminServiceImpl.updateCarDetails(carDetails, request, carId);

		assertEquals("BMW 655D", addCarDetails.getName());
	}

	@Test
	void testDeleteCarDetails() {

		CarDetails carDetails = new CarDetails();
		carDetails.setId(2);
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
		carDetails.setAdminName("Nagesh11");

		Mockito.lenient().when(carDao.findById(2)).thenReturn(carDetails);
		boolean deleteCarDetails = adminServiceImpl.deleteCarDetails(2);
		assertTrue(deleteCarDetails);

	}

	@Test
	void testCheckIfUsernameExists() {
		AdminDetails admin = new AdminDetails();
		admin.setId(1);
		admin.setUsername("Surya12");
		admin.setPassword("Surya@12");
		admin.setRole("ROLE_ADMIN");

		when(adminDao.findByUsername("Surya12")).thenReturn(admin);

		boolean checkIfUsernameExists = adminServiceImpl.checkIfUsernameExists("Surya12");

		assertTrue(checkIfUsernameExists);
	}

	@Test
	void testAdminDetails() {

		AdminDetails admin = new AdminDetails();
		admin.setId(1);
		admin.setUsername("Surya12");
		admin.setPassword("Surya@12");
		admin.setRole("ROLE_ADMIN");

		when(adminDao.findByUsername("Surya12")).thenReturn(admin);

		AdminDetails adminDetails = adminServiceImpl.adminDetails("Surya12");
		assertEquals("Surya@12", adminDetails.getPassword());

	}

}

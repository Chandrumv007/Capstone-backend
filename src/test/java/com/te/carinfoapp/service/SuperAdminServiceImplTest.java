package com.te.carinfoapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.te.carinfoapp.dao.AdminDao;
import com.te.carinfoapp.dao.CarDetailsDao;
import com.te.carinfoapp.dto.CarDetails;

@ExtendWith(MockitoExtension.class)
class SuperAdminServiceImplTest {
	
	@InjectMocks
	private SuperAdminServiceImpl superAdminServiceImpl;
	
	@Mock
	private CarDetailsDao carDao;
	
	@Mock
	private AdminDao adminDao;

	@Test
	void testGetAllCarDetails() {
		
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
		carDetails.setAdminName("Nagesh11");
		
		List<CarDetails> details=new ArrayList<CarDetails>();
		details.add(carDetails);
		
		when(carDao.findAll()).thenReturn(details);
		
		List<CarDetails> details2= superAdminServiceImpl.getAllCarDetails();
	
		assertEquals("Diesel", details2.get(0).getFuelType());
	}

}

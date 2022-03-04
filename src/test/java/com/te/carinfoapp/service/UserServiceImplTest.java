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


import com.te.carinfoapp.dao.CarDetailsDao;
import com.te.carinfoapp.dto.CarDetails;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Mock
	private CarDetailsDao carDao;

	@Test
	void testGetAllCarInfo() {

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
		
		List<CarDetails> allCarInfo = userServiceImpl.getAllCarInfo();
		
		assertEquals("BMW 650D", allCarInfo.get(0).getName());
		
	}

	@Test
	void testSearchCarInfo() {
		
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
		
		Mockito.lenient().when( carDao.findByFuelType("Electric")).thenReturn(details);
		Mockito.lenient().when(carDao.findByCompany("BMW")).thenReturn(details);
		Mockito.lenient().when(carDao.findByName("BMW 650D")).thenReturn(details);
		
		List<CarDetails> searchCarInfo = userServiceImpl.SearchCarInfo("Electric");
//		List<CarDetails> searchCarInfo1 = userServiceImpl.SearchCarInfo("BMW");
//		List<CarDetails> searchCarInfo2 = userServiceImpl.SearchCarInfo("BMW 650D");
		assertEquals("BMW 650D", searchCarInfo.get(0).getName());

	}

}

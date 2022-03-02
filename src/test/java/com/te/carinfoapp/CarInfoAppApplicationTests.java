package com.te.carinfoapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.te.carinfoapp.dao.AdminDao;
import com.te.carinfoapp.dao.CarDetailsDao;
import com.te.carinfoapp.dto.AdminDetails;
import com.te.carinfoapp.dto.CarDetails;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CarInfoAppApplicationTests {
	
	@Autowired
	private CarDetailsDao carDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Test
	@Order(1)
	public void checkSaveAdminDetailsTest() {
		AdminDetails admin=new AdminDetails();
		admin.setUsername("Surya12");
		admin.setPassword("Surya@12");
		admin.setRole("ROLE_ADMIN");
		adminDao.save(admin);
		assertNotNull(adminDao.findById(13));
		
	}
	@Test
	@Order(2)
	public void checkGetAllAdminDetailsTest() {
		List<AdminDetails> adminDetails=(List<AdminDetails>) adminDao.findAll();
		assertThat(adminDetails).size().isGreaterThan(3);
		
	}
	
	@Test
	@Order(3)
	public void checkUpdateAdminDetailsTest() {
		AdminDetails admin = adminDao.findById(13);
		admin.setUsername("Surya45");
		adminDao.save(admin);
		assertNotEquals("Surya12", adminDao.findById(13).getUsername());
		
	}
	
	@Test
	@Order(4)
	public void checkDeleteAdminDetailsTest() {
		adminDao.deleteById(13);
		assertThat(adminDao.existsById(13)).isFalse();
		
	}
	
	@Test
	@Order(5)
	public void checkSaveCarDetailsTest() {
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
		
		carDao.save(carDetails);
		assertNotNull(carDao.findById(13));
		
	}
	
	@Test
	@Order(6)
	public void checkGetAllCarDetailsTest() {
		List<CarDetails> carDetails=(List<CarDetails>) carDao.findAll();
		assertThat(carDetails).size().isGreaterThan(9);
		
	}
	
	@Test
	@Order(7)
	public void checkUpdateCarDetailsTest() {
		CarDetails car = carDao.findById(13);
		car.setSeatingCapacity(6);
		carDao.save(car);
		assertNotEquals(4, carDao.findById(13).getSeatingCapacity());
		
	}
	
	@Test
	@Order(8)
	public void checkDeleteCarDetailsTest() {
		carDao.deleteById(13);
		assertThat(carDao.existsById(13)).isFalse();
		
	}
	
	

	
}

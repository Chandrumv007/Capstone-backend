package com.te.carinfoapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.carinfoapp.dao.CarDetailsDao;
import com.te.carinfoapp.dto.CarDetails;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private CarDetailsDao carDao;

	@Override
	public List<CarDetails> getAllCarInfo() {
		return (List<CarDetails>) carDao.findAll();
	}

	@Override
	public List<CarDetails> SearchCarInfo(String searchData) {
		String search = searchData.toUpperCase();
		if (search.startsWith("ELECTRIC")) {
			return (List<CarDetails>) carDao.findByFuelType("Electric");
		}

		if (search.startsWith("DIESEL")) {
			return (List<CarDetails>) carDao.findByFuelType("Diesel");
		}
		if (search.startsWith("PETROL")) {
			return (List<CarDetails>) carDao.findByFuelType("Petrol");
		}
		if (search.equalsIgnoreCase("BMW") || search.equalsIgnoreCase("Mercedes") || search.equalsIgnoreCase("Audi")
				|| search.equalsIgnoreCase("Maruthi")|| search.equalsIgnoreCase("Toyota") || search.equalsIgnoreCase("Suzuki")
				|| search.equalsIgnoreCase("Tata")) {
			return (List<CarDetails>) carDao.findByCompany(search);
		}
		if (search.equalsIgnoreCase("BMW 6520") || search.equalsIgnoreCase("Bmw 500D")
				|| search.equalsIgnoreCase("Benz S400") || search.equalsIgnoreCase("Audi q7")
				|| search.equalsIgnoreCase("Nano") || search.equalsIgnoreCase("Maruthi 800")
				|| search.equalsIgnoreCase("Brezza")|| search.equalsIgnoreCase("Toyota Hybrid Electric")
				|| search.equalsIgnoreCase("Swift Desire")) {
			return (List<CarDetails>) carDao.findByName(search);
		}
		return null;

	}

}

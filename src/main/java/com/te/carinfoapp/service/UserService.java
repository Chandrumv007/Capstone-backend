package com.te.carinfoapp.service;

import java.util.List;

import com.te.carinfoapp.dto.CarDetails;

public interface UserService {
	
	List<CarDetails> getAllCarInfo();
	List<CarDetails> SearchCarInfo(String searchData);

}

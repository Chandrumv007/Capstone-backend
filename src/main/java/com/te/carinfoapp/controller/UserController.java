package com.te.carinfoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.carinfoapp.dto.CarDetails;
import com.te.carinfoapp.dto.UserResponse;
import com.te.carinfoapp.service.UserService;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/car/info")
	public ResponseEntity<?> getAllCarInfo() {
		try {
			List<CarDetails> allCarInfo = userService.getAllCarInfo();
			return ResponseEntity.ok(new UserResponse(false, "success", allCarInfo, null));
		} catch (Exception e) {
			return new ResponseEntity<UserResponse>(new UserResponse(true, "No Data Available", null, null),HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/car/search")
	public ResponseEntity<?> SearchCarInfo( String searchData) {

		List<CarDetails> searchCarInfo = userService.SearchCarInfo(searchData);
		if (searchCarInfo != null) {
			return ResponseEntity.ok(new UserResponse(false, "success", null, searchCarInfo));
		} else {
			return new ResponseEntity<UserResponse>(new UserResponse(true, "No search Results", null, null),HttpStatus.BAD_REQUEST);
		}

	}

}

package com.te.carinfoapp.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class CarDetailsResponseTest {

	private ObjectMapper mapper = new ObjectMapper();
	String json = "{\"error\":false,\"message\":\"success\",\"allCarDetails\":[{\"id\":1,\"name\":\"BMW 650D\",\"company\":\"BMW\",\"fuelType\":\"Diesel\",\"powerSteering\":true,\"breakSystem\":\"ABS\",\"showroomPrice\":2000000.0,\"onroadPrice\":null,\"imageURL\":\"https//sjdfsjakeurgdheahgh\",\"mileage\":20.0,\"seatingCapacity\":4,\"engineCapacity\":1000,\"gearType\":\"Automatic\",\"adminName\":null}]}"
			+ "";

	@Test
	void serializeTest() throws JsonProcessingException {

		CarDetailsResponse readValue = mapper.readValue(json, CarDetailsResponse.class);
		String writeValueAsString = mapper.writeValueAsString(readValue);
		assertEquals(json, writeValueAsString);
	}

	@Test
	void deSerializeTest() throws JsonMappingException, JsonProcessingException {
		CarDetailsResponse readValue = mapper.readValue(json, CarDetailsResponse.class);
		assertEquals("BMW 650D", readValue.getAllCarDetails().get(0).getName());
	}

}

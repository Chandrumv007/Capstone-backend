package com.te.carinfoapp.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class UserResponseTest {

	private ObjectMapper mapper = new ObjectMapper();
	String json = "{\"error\":true,\"message\":\"No Data Available\"}";

	@Test
	void serializeTest() throws JsonProcessingException {

		UserResponse readValue = mapper.readValue(json, UserResponse.class);
		String writeValueAsString = mapper.writeValueAsString(readValue);
		assertEquals(json, writeValueAsString);
	}

	@Test
	void deSerializeTest() throws JsonMappingException, JsonProcessingException {
		UserResponse readValue = mapper.readValue(json, UserResponse.class);
		assertEquals("No Data Available", readValue.getMessage());
	}
}

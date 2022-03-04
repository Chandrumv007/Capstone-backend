package com.te.carinfoapp.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class SuperAdminResponseTest {

	private ObjectMapper mapper = new ObjectMapper();
	String json = "{\"error\":true,\"message\":\"Data not Available\"}";

	@Test
	void serializeTest() throws JsonProcessingException {

		SuperAdminResponse readValue = mapper.readValue(json, SuperAdminResponse.class);
		String writeValueAsString = mapper.writeValueAsString(readValue);
		assertEquals(json, writeValueAsString);
	}

	@Test
	void deSerializeTest() throws JsonMappingException, JsonProcessingException {
		SuperAdminResponse readValue = mapper.readValue(json, SuperAdminResponse.class);
		assertEquals("Data not Available", readValue.getMessage());
	}

}

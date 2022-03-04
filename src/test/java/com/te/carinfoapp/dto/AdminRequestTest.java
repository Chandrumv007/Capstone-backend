package com.te.carinfoapp.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class AdminRequestTest {

	private ObjectMapper mapper = new ObjectMapper();
	String json = "{\"username\":\"Chandru007\",\"password\":\"Chandru@123\"}";

	@Test
	void serializeTest() throws JsonProcessingException {
		AdminRequest readValue = mapper.readValue(json, AdminRequest.class);
		String writeValueAsString = mapper.writeValueAsString(readValue);
		assertEquals(json, writeValueAsString);

	}
	@Test
	void deSerializeTest() throws JsonMappingException, JsonProcessingException {
		AdminRequest readValue = mapper.readValue(json, AdminRequest.class);
		assertEquals("Chandru007", readValue.getUsername());
	}

}

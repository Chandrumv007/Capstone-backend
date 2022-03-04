package com.te.carinfoapp.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class AdminDetailsTest {

	private String json="{\"id\":1,\"username\":\"user\",\"password\":\"1234\",\"role\":\"ROLE_ADMIN\"}";
	private ObjectMapper mapper=new ObjectMapper();
	@Test
	void serializeTest() throws JsonProcessingException {
		
		AdminDetails readValue = mapper.readValue(json, AdminDetails.class);
		String writeValueAsString = mapper.writeValueAsString(readValue);
		assertEquals(json, writeValueAsString);
	}
	
	@Test
	void deSerializeTest() throws JsonMappingException, JsonProcessingException {
		AdminDetails readValue = mapper.readValue(json, AdminDetails.class);
		assertEquals(1, readValue.getId());
	}

}

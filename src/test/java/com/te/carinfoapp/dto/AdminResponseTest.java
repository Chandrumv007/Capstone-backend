package com.te.carinfoapp.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class AdminResponseTest {
	private ObjectMapper mapper = new ObjectMapper();
	String json = "{\"error\":false,\"message\":\"success\",\"token\":\"jwt\",\"role\":\"role\"}";

	@Test
	void serializeTest() throws JsonProcessingException {
//		AdminResponse adminResponse=new AdminResponse(false, "success", "jwt", "role");
//		String writeValueAsString = mapper.writeValueAsString(adminResponse);
//		System.out.println(writeValueAsString);

		AdminResponse readValue = mapper.readValue(json, AdminResponse.class);
		String writeValueAsString = mapper.writeValueAsString(readValue);
		assertEquals(json, writeValueAsString);
	}

	@Test
	void deSerializeTest() throws JsonMappingException, JsonProcessingException {
		AdminResponse readValue = mapper.readValue(json, AdminResponse.class);
		assertEquals("success", readValue.getMessage());
	}

}

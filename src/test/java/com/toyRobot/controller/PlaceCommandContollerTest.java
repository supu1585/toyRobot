package com.toyRobot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.toyRobot.service.MoveCommandService;

@SpringBootTest
public class PlaceCommandContollerTest {

	public PlaceCommandContollerTest() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	MoveCommandService moveCommandService;

	String expected = null;

	RestTemplate restTemplate = new RestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void placeRobotOnTable_MoveCommandTest() {
		expected = "Robot placed at 0, 0, NORTH";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(("http://localhost:8080/place/0/0/NORTH"),
				HttpMethod.POST, entity, String.class);
		assertEquals(expected, response.getBody());
	}

	@Test
	public void placeRobotOffTable_X_MoveCommandTest() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		assertThrows(IllegalArgumentException.class, () -> restTemplate
				.exchange((";http://localhost:8080/place/6/4/NORTH"), HttpMethod.POST, entity, String.class));
	}

	@Test
	public void placeRobotOffTable_Y_MoveCommandTest() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		assertThrows(IllegalArgumentException.class, () -> restTemplate
				.exchange((";http://localhost:8080/place/4/6/NORTH"), HttpMethod.POST, entity, String.class));
	}
	
	@Test
	public void placeRobotWithInvalidDirection_MoveCommandTest() {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		assertThrows(IllegalArgumentException.class, () -> restTemplate
				.exchange((";http://localhost:8080/place/4/6/NOR"), HttpMethod.POST, entity, String.class));
	}
}

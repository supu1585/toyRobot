package com.toyRobot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.toyRobot.exception.RobotNotFoundException;
import com.toyRobot.service.MoveCommandService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MoveCommandControllerTest {

	@Autowired
	MoveCommandService moveCommandService;

	String expected = null;

	RestTemplate restTemplate = new RestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	@Order(1)
	public void whenNoRobotPresent_placeMoveCommandTest() {
		assertThrows(RobotNotFoundException.class, () -> {
			moveCommandService.move();
		});
	}

	@Test
	@Order(2)
	public void whenNoRobotPresent_placeLeftCommandTest() {
		assertThrows(RobotNotFoundException.class, () -> {
			moveCommandService.left();
		});
	}

	@Test
	@Order(3)
	public void whenNoRobotPresent_placeRightCommandTest() {
		assertThrows(RobotNotFoundException.class, () -> {
			moveCommandService.right();
		});
	}

	@Test
	@Order(4)
	public void whenRobotPresent_placeMoveCommandTest() {
		expected = "Moved to 0, 1, NORTH";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		restTemplate.exchange(("http://localhost:8080/place/0/0/NORTH"), HttpMethod.POST, entity, String.class);

		ResponseEntity<String> response = restTemplate.exchange(("http://localhost:8080/move"), HttpMethod.PUT, entity,
				String.class);
		assertEquals(expected, response.getBody());
	}

	@Test
	@Order(5)
	public void whenRobotPresent_placeLeftCommandTest() {
		expected = "Direction set as WEST";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		restTemplate.exchange(("http://localhost:8080/place/0/0/NORTH"), HttpMethod.POST, entity, String.class);

		ResponseEntity<String> response = restTemplate.exchange(("http://localhost:8080/left"), HttpMethod.PUT, entity,
				String.class);
		assertEquals(expected, response.getBody());
	}

	@Test
	@Order(6)
	public void whenRobotPresent_placeRightCommandTest() {
		expected = "Direction set as EAST";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		restTemplate.exchange(("http://localhost:8080/place/0/0/NORTH"), HttpMethod.POST, entity, String.class);

		ResponseEntity<String> response = restTemplate.exchange(("http://localhost:8080/right"), HttpMethod.PUT, entity,
				String.class);
		assertEquals(expected, response.getBody());
	}
}

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

import com.toyRobot.exception.RobotNotFoundException;
import com.toyRobot.service.ReportCommandService;

@SpringBootTest
public class ReportCommandControllerTest {

	public ReportCommandControllerTest() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	ReportCommandService reportCommandService;

	String expected = null;

	RestTemplate restTemplate = new RestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void whenNoRobotPresent_placeReportCommandTest() {
		assertThrows(RobotNotFoundException.class, () -> {
			reportCommandService.report();
		});
	}

	@Test
	public void whenRobotPresent_placeReportCommandTest() {
		expected = "0, 0, NORTH";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		restTemplate.exchange(("http://localhost:8080/place/0/0/NORTH"), HttpMethod.POST, entity, String.class);

		ResponseEntity<String> response = restTemplate.exchange(("http://localhost:8080/report"), HttpMethod.GET,
				entity, String.class);
		assertEquals(expected, response.getBody());
	}

	@Test
	public void placeReportCommandAfterMoveCommand() {
		expected = "0, 1, NORTH";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		restTemplate.exchange(("http://localhost:8080/place/0/0/NORTH"), HttpMethod.POST, entity, String.class);
		restTemplate.exchange(("http://localhost:8080/move"), HttpMethod.PUT, entity, String.class);

		ResponseEntity<String> response = restTemplate.exchange(("http://localhost:8080/report"), HttpMethod.GET,
				entity, String.class);
		assertEquals(expected, response.getBody());
	}
	
	@Test
	public void placeReportCommandAfterLeftCommand() {
		expected = "0, 0, WEST";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		restTemplate.exchange(("http://localhost:8080/place/0/0/NORTH"), HttpMethod.POST, entity, String.class);
		restTemplate.exchange(("http://localhost:8080/left"), HttpMethod.PUT, entity, String.class);

		ResponseEntity<String> response = restTemplate.exchange(("http://localhost:8080/report"), HttpMethod.GET,
				entity, String.class);
		assertEquals(expected, response.getBody());
	}
	
	@Test
	public void placeReportCommandAfterRightCommand() {
		expected = "0, 0, EAST";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		restTemplate.exchange(("http://localhost:8080/place/0/0/NORTH"), HttpMethod.POST, entity, String.class);
		restTemplate.exchange(("http://localhost:8080/right"), HttpMethod.PUT, entity, String.class);

		ResponseEntity<String> response = restTemplate.exchange(("http://localhost:8080/report"), HttpMethod.GET,
				entity, String.class);
		assertEquals(expected, response.getBody());
	}
}

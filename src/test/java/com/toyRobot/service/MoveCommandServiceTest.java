package com.toyRobot.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.toyRobot.exception.RobotNotFoundException;
import com.toyRobot.model.SquareTable;
import com.toyRobot.repository.RobotCommandRepository;

@RunWith(SpringRunner.class)
public class MoveCommandServiceTest {

	@InjectMocks
	MoveCommandService moveCommandService = new MoveCommandService();

	@Mock
	RobotCommandRepository robotCommandRepository;

	@Mock
	SquareTable squareTable;

	@BeforeEach
	void init_mocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void validateAndPopulateRobotExceptionTest() {
		when(robotCommandRepository.findTopByOrderByIdDesc()).thenReturn(java.util.Optional.ofNullable(null));
		assertThrows(RobotNotFoundException.class, () -> {
			moveCommandService.move();
		});

	}
}

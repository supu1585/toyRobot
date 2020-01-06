package com.toyRobot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toyRobot.exception.RobotNotFoundException;
import com.toyRobot.model.Robot;
import com.toyRobot.repository.RobotCommandRepository;

@Service
public class ReportCommandService {

	@Autowired
	private RobotCommandRepository robotCommandRepository;

	private Robot robot;

	/**
	 * Report the current cords and the facing
	 * 
	 * @return resulting fail/success message
	 */
	public String report() {
		Optional<Robot> robotOptional = robotCommandRepository.findTopByOrderByIdDesc();
		String reportDetails = null;
		if (robotOptional.isPresent()) {
			robot = robotOptional.get();
			reportDetails = robot.getCurrentStatus();
		}

		return Optional.ofNullable(reportDetails).orElseThrow(RobotNotFoundException::new);
	}
}

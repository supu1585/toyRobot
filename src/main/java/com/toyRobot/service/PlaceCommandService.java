package com.toyRobot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.toyRobot.model.Direction;
import com.toyRobot.model.Robot;
import com.toyRobot.model.SquareTable;
import com.toyRobot.repository.RobotCommandRepository;

@Service
public class PlaceCommandService {

	@Autowired
	SquareTable squareTable;

	@Autowired
	private RobotCommandRepository robotCommandRepository;

	/**
	 * Places a robot at given coordinates on the table.
	 *
	 * @param xCoordinate to be used as X coordinate
	 * @param yCoordinate to be used as Y coordinate
	 * @param direction   as facing on robot
	 * @return resulting fail/success message
	 */
	public String place(int xCoordinate, int yCoordinate, Direction direction) {
		if (squareTable.isPositionValid(xCoordinate, yCoordinate) && !StringUtils.isEmpty(direction)) {
			Robot robot = new Robot(xCoordinate, yCoordinate, direction);
			robotCommandRepository.save(robot);
			return "Robot placed at " + robot.getCurrentStatus();
		} else {
			return "ROBOT can't be placed";
		}
	}
}

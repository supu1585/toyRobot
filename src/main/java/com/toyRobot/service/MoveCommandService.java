package com.toyRobot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toyRobot.exception.InvalidPositionException;
import com.toyRobot.exception.RobotNotFoundException;
import com.toyRobot.model.Direction;
import com.toyRobot.model.Robot;
import com.toyRobot.model.SquareTable;
import com.toyRobot.repository.RobotCommandRepository;

@Service
public class MoveCommandService {
	
	public MoveCommandService() {
		super();
	}

	@Autowired
	SquareTable squareTable;

	@Autowired
	RobotCommandRepository robotCommandRepository;

	private Robot robot;

	private Direction direction;

	/**
	 * Moves the robot in the Direction direction if possible
	 * 
	 * @return resulting fail/success message
	 */
	public String move() {
		populateRobot();

		// temporary coords
		int tmpX = robot.getxCordinate();
		int tmpY = robot.getyCordinate();
		switch (robot.getDirection()) {
		case NORTH:
			// Direction north, increase y
			tmpY++;
			break;
		case SOUTH:
			// Direction south, decrease y
			tmpY--;
			break;
		case EAST:
			// Direction east, increase x
			tmpX++;
			break;
		case WEST:
			// Direction west, decrease x
			tmpX--;
			break;
		}
		// if temp coords are valid for move
		if (squareTable.isPositionValid(tmpX, tmpY)) {
			// assign them to the real coords
			robot.setxCordinate(tmpX);
			robot.setyCordinate(tmpY);
			robotCommandRepository.save(robot);
			return "Moved to " + robot.getCurrentStatus();
		} else {
			 throw new InvalidPositionException("Cannot move to " + robot.getCurrentStatus());
		}
	}

	/**
	 * Rotates the robot to the left
	 * 
	 * @return resulting fail/success message
	 */
	public String left() {
		
		populateRobot();

		switch (robot.getDirection()) {
		case NORTH:
			direction = Direction.WEST;
			break;
		case SOUTH:
			direction = Direction.EAST;
			break;
		case EAST:
			direction = Direction.NORTH;
			break;
		case WEST:
			direction = Direction.SOUTH;
			break;
		}
		robot.setDirection(direction);
		robotCommandRepository.save(robot);
		return robot.getDirectionStatus();
	}

	/**
	 * Rotates the robot to the right
	 * 
	 * @return resulting fail/success message
	 */
	public String right() {
		populateRobot();

		switch (robot.getDirection()) {
		case NORTH:
			direction = Direction.EAST;
			break;
		case SOUTH:
			direction = Direction.WEST;
			break;
		case EAST:
			direction = Direction.SOUTH;
			break;
		case WEST:
			direction = Direction.NORTH;
			break;
		}
		robot.setDirection(direction);
		robotCommandRepository.save(robot);
		return robot.getDirectionStatus();
	}

	/**
	 * Populate Robot details
	 */
	private void populateRobot() {
		Optional<Robot> robotOptional = robotCommandRepository.findTopByOrderByIdDesc();
		if (robotOptional.isPresent()) {
			robot = robotOptional.get();
		} else {
			throw new RobotNotFoundException("ROBOT NOT FOUND");
		}
	}

}

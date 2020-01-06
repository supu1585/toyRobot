package com.toyRobot.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RobotNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3808528699668008217L;

	public RobotNotFoundException(String message) {
		super(message);
	}
	
	public RobotNotFoundException() {
		super("ROBOT NOT FOUND");
	}

}
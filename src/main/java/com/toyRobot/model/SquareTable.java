package com.toyRobot.model;

import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class SquareTable {
	
	public SquareTable() {
		
	}

	public static final Integer MAX_X_COORDINATE = 5;

	public static final Integer MAX_Y_COORDINATE = 5;

	
	/**
	 * @param xCoOrdinate
	 * @param yCoOrdinate
	 * @return true if x & y are in 0-5 range else return false.
	 */
	public boolean isPositionValid(int xCoOrdinate, int yCoOrdinate) {
		// both x and y can be the values from the range [0, 5)
		return Optional.ofNullable(xCoOrdinate).filter(n -> n >= 0).filter(n -> n <= MAX_Y_COORDINATE).isPresent()
				&& Optional.ofNullable(yCoOrdinate).filter(n -> n >= 0).filter(n -> n <= MAX_Y_COORDINATE).isPresent();
	}
}

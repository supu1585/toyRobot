package com.toyRobot.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyRobot.model.Direction;
import com.toyRobot.service.PlaceCommandService;

@RestController
@Validated
public class PlaceCommandController {
	
	final static String X_VALID = "X coordinate must be between 0 and 5";
	final static String Y_VALID = "Y coordinate must be between 0 and 5";

	@Autowired
	private PlaceCommandService placeCommandService;

	@PostMapping("/place/{x}/{y}/{f}")
	public String placeCommand(@PathVariable("x") @Min(value= 0 ,message=X_VALID) @Max(value= 5 ,message= X_VALID) int x,
							   @PathVariable("y") @Min(value= 0 ,message=Y_VALID) @Max(value= 5 ,message= Y_VALID) int y,
							   @PathVariable("f") Direction direction) {
		return placeCommandService.place(x, y, direction);
	}

}

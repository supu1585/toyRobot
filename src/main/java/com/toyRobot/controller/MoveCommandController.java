package com.toyRobot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyRobot.service.MoveCommandService;

@RestController
public class MoveCommandController {

	@Autowired
	MoveCommandService moveCommandService;
	
	@PutMapping("/move")
	public String moveCommand() {

		return moveCommandService.move();
	}
	
	@PutMapping("/left")
	public String leftCommand() {

		return moveCommandService.left();
	}
	
	@PutMapping("/right")
	public String rightCommand() {

		return moveCommandService.right();
	}
}

package com.toyRobot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toyRobot.service.ReportCommandService;

@RestController
public class ReportCommandController {

	@Autowired
	ReportCommandService reportCommandService;
	
	@GetMapping("/report")
	public String reportCommand() {

		return reportCommandService.report();
	}
}

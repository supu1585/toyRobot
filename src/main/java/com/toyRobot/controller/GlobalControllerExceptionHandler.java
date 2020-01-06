package com.toyRobot.controller;

import java.util.Date;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.toyRobot.exception.ApiErrorResponse;
import com.toyRobot.exception.InvalidPositionException;
import com.toyRobot.exception.RobotNotFoundException;

@ControllerAdvice(basePackageClasses = GlobalControllerExceptionHandler.class)
@ResponseBody
public class GlobalControllerExceptionHandler extends ExceptionHandlerExceptionResolver {

	@ExceptionHandler(ConversionFailedException.class)
	public ResponseEntity<ApiErrorResponse> handleDescriptionNotFound(RuntimeException ex, WebRequest request) {
		ApiErrorResponse errorDetails = new ApiErrorResponse(new Date(), "Please enter valid direction.",
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RobotNotFoundException.class)
	public ResponseEntity<ApiErrorResponse> handleRobotNotFound(RuntimeException ex, WebRequest request) {
		ApiErrorResponse errorDetails = new ApiErrorResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidPositionException.class)
	public ResponseEntity<ApiErrorResponse> handleInvalidPosition(RuntimeException ex, WebRequest request) {
		ApiErrorResponse errorDetails = new ApiErrorResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
	}

}

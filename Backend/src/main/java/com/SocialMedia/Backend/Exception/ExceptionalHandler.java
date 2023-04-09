package com.SocialMedia.Backend.Exception;

;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionalHandler {

	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<CustomErrorDetails> noHandler(NoHandlerFoundException noHandler, WebRequest request) {
		CustomErrorDetails CustomErrorDetails = new CustomErrorDetails(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				"Please enter valid URL", request.getDescription(false));

		return new ResponseEntity<>(CustomErrorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<CustomErrorDetails> httpRequestMethodException(HttpRequestMethodNotSupportedException exception,
			WebRequest request) {

		CustomErrorDetails errorDetail = new CustomErrorDetails(LocalDateTime.now(), HttpStatus.METHOD_NOT_ALLOWED.value(),
				exception.getMessage(), request.getDescription(false));

		return new ResponseEntity<CustomErrorDetails>(errorDetail, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomErrorDetails> resourceNotFoundException(ResourceNotFoundException resourceNotFoundException,
																		WebRequest webRequest) {

		CustomErrorDetails CustomErrorDetails = new CustomErrorDetails(LocalDateTime.now(), HttpStatus.CONFLICT.value(),
				"resource Not Found",resourceNotFoundException.getMessage());
		return new ResponseEntity<CustomErrorDetails>(CustomErrorDetails, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomErrorDetails> validationExceptionHandler(
			MethodArgumentNotValidException validationException, WebRequest webRequest) {

		CustomErrorDetails err = new CustomErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Validation Error",
				validationException.getMessage());

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<CustomErrorDetails> myMANVExceptionHandler(
			IllegalArgumentException illegalArgumentException, WebRequest webRequest) {

		CustomErrorDetails err = new CustomErrorDetails(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Validation Error",
				illegalArgumentException.getMessage());

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}


	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<CustomErrorDetails> userAlreadyExistException(ResourceAlreadyExistException resourceAlreadyExistException,
			WebRequest webRequest) {

		CustomErrorDetails CustomErrorDetails = new CustomErrorDetails(LocalDateTime.now(), HttpStatus.CONFLICT.value(),
				"resource Already Exist",resourceAlreadyExistException.getMessage());
		return new ResponseEntity<CustomErrorDetails>(CustomErrorDetails, HttpStatus.CONFLICT);
	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomErrorDetails> generalException(Exception exception, WebRequest request) {

		CustomErrorDetails errorDetail = new CustomErrorDetails(LocalDateTime.now(), HttpStatus.METHOD_NOT_ALLOWED.value(),
				"BAD REQUEST", request.getDescription(false));

		return new ResponseEntity<CustomErrorDetails>(errorDetail, HttpStatus.METHOD_NOT_ALLOWED);
	}
}

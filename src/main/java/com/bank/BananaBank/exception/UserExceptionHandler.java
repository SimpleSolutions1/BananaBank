package com.bank.BananaBank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bank.BananaBank.model.dto.ErrorMessageDTO;

@ControllerAdvice
public class UserExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorMessageDTO> handleException(UserNotFoundException exc) {
		
		ErrorMessageDTO error = new ErrorMessageDTO(HttpStatus.NOT_FOUND.value(), exc.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorMessageDTO> handleException(Exception exc) {

		ErrorMessageDTO error = new ErrorMessageDTO(HttpStatus.BAD_REQUEST.value(), exc.getMessage());		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
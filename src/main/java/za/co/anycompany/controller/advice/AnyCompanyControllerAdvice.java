package za.co.anycompany.controller.advice;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import za.co.anycompany.dto.CustomExceptionResponse;
import za.co.anycompany.exception.BusinessException;
import za.co.anycompany.exception.CustomerNotFoundException;

@ControllerAdvice
public class AnyCompanyControllerAdvice {

	private static final String VALIDATION_FAILED_ERROR_MESSAGE = "Validation failed.";

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<CustomExceptionResponse> customerNotFoundExceptionHandler(
			CustomerNotFoundException exception) {

		CustomExceptionResponse exceptionResponse = CustomExceptionResponse.builder().message(exception.getMessage())
				.details(exception.getDetails()).build();

		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<CustomExceptionResponse> businessExceptionHandler(BusinessException exception) {
		CustomExceptionResponse exceptionResponse = CustomExceptionResponse.builder().message(exception.getMessage())
				.details(exception.getDetails()).build();

		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomExceptionResponse> validationExceptionHandler(
			MethodArgumentNotValidException exception) {
		CustomExceptionResponse exceptionResponse = CustomExceptionResponse.builder()
				.details(exception.getAllErrors().stream().map(error -> error.getCode()).collect(Collectors.toList()))
				.message(VALIDATION_FAILED_ERROR_MESSAGE).build();
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}

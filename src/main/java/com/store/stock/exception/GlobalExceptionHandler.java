package com.store.stock.exception;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * GlobalExceptionHandler - we are handled here the global exceptions concepts
 * for validations error handled with handleMethodArgumentNotValid exception
 * 
 * 
 * @author Govindasamy.C
 * @version V1.1
 * @since 23-12-2019
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * get the validation filed errors while sending params in the method of the
	 * request body params. In this case, we can validate the each field error
	 * values.
	 * 
	 * @return response entity object for setting the response values with status,
	 *         errors with body
	 */
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", status.value());

		// Get all errors for field valid
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());

		body.put("errors", errors);
		return new ResponseEntity<>(body, headers, status);
	}
}

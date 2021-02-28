package org.aspedemo.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AspeExceptionAdvisor extends ResponseEntityExceptionHandler {
	
	/**
	 * Constructs an error response containing all information available
	 * from the exception in its body.
	 * 
	 * @param ex  Exception thrown by the controller.
	 * @return the response body.
	 */
	@ExceptionHandler(AspeException.class)
	public ResponseEntity<Object> handleException(AspeException ex) {
		
		// get information from the exception
		HttpStatus       status = ex.getStatus();
		String           msg    = ex.getMessage();
		Optional<String> error  = ex.getError();
		List<String>     errors = ex.getErrors();
		
		// create the response body
		Map<String, Object> body = new HashMap<>();
		body.put("status", status.value() + " " + status.getReasonPhrase());
		body.put("message", msg);
		if (error.isPresent()) {
			body.put("error", error);
		}
		if (!errors.isEmpty()) {
			body.put("errors", errors);
		}
		
		// create and return the response
		return new ResponseEntity<>(body, status);
	}
}

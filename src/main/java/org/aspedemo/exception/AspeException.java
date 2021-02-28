package org.aspedemo.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

/**
 * This exception represents REST error responses.
 * 
 * A RESTful error response can be created by throwing an exception of this class.
 * 
 * Objects of this class are transformed to an error response automatically
 * by {@link AspeExceptionAdvisor#handleException(AspeException)}.
 */
public class AspeException extends RuntimeException {
	private HttpStatus       status;
	private String           message = "Unknown error";
	private List<String>     errors  = new ArrayList<String>();
	private Optional<String> error;
	
	public AspeException(int statusCode) {
		setStatus(statusCode);
	}
	public AspeException(int statusCode, String msg) {
		setStatus(statusCode);
		setMessage(msg);
	}
	public AspeException(int statusCode, String msg, String error) {
		setStatus(statusCode);
		setMessage(msg);
		setError(error);
	}
	
	private void setStatus(int statusCode) {
		this.status = HttpStatus.resolve(statusCode);
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	public void setError(String error) {
		this.error = Optional.of(error);
	}
	public void addError(String error) {
		this.errors.add(error);
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	public Optional<String> getError() {
		return error;
	}
	public List<String> getErrors() {
		return errors;
	}
}

package mca.rest.api.error;

import org.springframework.http.HttpStatus;


public class ApiError {

	private HttpStatus status;
	private String message;

	private ApiError() {
		super();
	};

	ApiError(HttpStatus status) {
		this();
		this.setStatus(status);
	}

	ApiError(HttpStatus status, String message) {
		this();
		this.setStatus(status);
		this.setMessage(message);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}

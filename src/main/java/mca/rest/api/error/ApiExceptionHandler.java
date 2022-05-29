package mca.rest.api.error;

import java.net.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected ResponseEntity<Object> handleNoHandlerFoundException(
			NoHandlerFoundException ex, 
            HttpHeaders headers, 
            HttpStatus status, 
            WebRequest request) {
	       ApiError apiError = new ApiError(
	    		   HttpStatus.NOT_FOUND,
	    		   "Page Not found");
	       return ResponseEntityBuilder.build(apiError);
	}
}
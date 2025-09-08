package jsp.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jsp.springboot.dto.ServerResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(NoRecordFoundException.class)
	public ResponseEntity<ServerResponse<String>> handleNoRecordFoundException(NoRecordFoundException exception) {
		ServerResponse<String> response = new ServerResponse<String>();
		
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage("Failure");
		response.setData(exception.getMessage());
		
		return new ResponseEntity<ServerResponse<String>> (response,HttpStatus.NOT_FOUND);
	}
}

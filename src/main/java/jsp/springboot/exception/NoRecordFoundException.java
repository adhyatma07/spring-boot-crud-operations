package jsp.springboot.exception;

public class NoRecordFoundException extends RuntimeException {
	
	public NoRecordFoundException(String message) {
		super(message);
	}
}

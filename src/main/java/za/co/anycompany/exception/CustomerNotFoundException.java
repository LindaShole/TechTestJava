package za.co.anycompany.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	private String details;
}

package za.co.anycompany.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class CustomExceptionResponse {

	private Object message;
	private Object details;
	
}

package za.co.anycompany.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse extends RuntimeException{
    private String message;

}

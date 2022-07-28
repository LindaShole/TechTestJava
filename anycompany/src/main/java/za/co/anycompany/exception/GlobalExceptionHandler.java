package za.co.anycompany.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ErrorMessage> errorMessages = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .flatMap(filterType(FieldError.class))
                .map(error -> new ErrorMessage(error.getObjectName(),
                        error.getField(), error.getDefaultMessage(), error.getRejectedValue().toString()))
                .collect(Collectors.toList());

        ErrorResponse response = new ErrorResponse(status.name(), status.value(), errorMessages);
        return new ResponseEntity<>(response, status);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {

        try {
            List<ErrorMessage> errors = ex.getConstraintViolations()
                    .stream()
                    .map(violation ->
                            new ErrorMessage(violation.getLeafBean().toString(),
                                    violation.getPropertyPath().toString(),
                                    violation.getMessage(),
                                    violation.getInvalidValue().toString()))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(List.of(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handleExceptions(Exception ex, WebRequest request) {
        ErrorDetail error = new ErrorDetail(LocalDate.now(), ex.getMessage(),
                ex.getCause() != null ? ex.getCause().toString(): "");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private static <T, R> Function<T, Stream<R>> filterType(Class<R> clazz) {
        return type -> clazz.isInstance(type) ? Stream.of(clazz.cast(type)) : Stream.empty();
    }

}

package za.co.anycompany.web.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException() {
        super();
    }

    public OrderNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public OrderNotFoundException(final String message) {
        super(message);
    }

    public OrderNotFoundException(final Throwable cause) {
        super(cause);
    }
}

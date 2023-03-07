package za.co.anycompany.anycompany.web.exception;

public class OrderIdMismatchException extends RuntimeException {
    public OrderIdMismatchException() {
        super();
    }

    public OrderIdMismatchException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public OrderIdMismatchException(final String message) {
        super(message);
    }

    public OrderIdMismatchException(final Throwable cause) {
        super(cause);
    }
}

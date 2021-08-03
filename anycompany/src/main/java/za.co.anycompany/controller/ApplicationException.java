package za.co.anycompany.controller;

/**
 * Created by jaco on 2017/03/05.
 */
public class ApplicationException extends RuntimeException {

    public enum Type {
        unexpected,
        client_error,
        constraint_violation,
        concurrent_update,
        entity_not_found,
        entity_already_exist,
        amount_error,
    }

    //-----[ GENERAL / UNSPECIFIED 0 - 19 ]------------------------------------------------------------------------------

    // Unspecified
    public static final int CODE_UNSPECIFIED = 0;
    public static final int CODE_CUSTOMER_ALREADY_EXIST = 1;
    public static final int CODE_CUSTOMER_NOT_FOUND = 2;
    public static final int CODE_ORDER_AMOUNT_NOT_ALLOWED = 3;

    private Type type;
    private int code = CODE_UNSPECIFIED;

    public ApplicationException(Type type, int code) {
        this.type = type;
        this.code = code;
    }

    public ApplicationException(Type type, int code, String message) {
        super(message);
        this.type = type;
        this.code = code;
    }

    public ApplicationException(Type type, int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public Type getType() {
        return type;
    }


    @Override
    public String toString() {
        return "ApplicationException{" +
                "type=" + type +
                '}';
    }
}

package za.co.anycompany.common.exception;

/**
 * <p>Title: NullConnectionException</p>
 *
 * <p>Description:  Custom exception class for handling failures to establish database connections</p>
 *
 * <p>Company: AnyCompany</p>
 *
 * @author Chizeba Maulu
 */
public class NullConnectionException extends Exception {
    /**
     * Constructor
     *
     * @param message the message
     **/
    public NullConnectionException(String message) {
        super(message);
    }
}
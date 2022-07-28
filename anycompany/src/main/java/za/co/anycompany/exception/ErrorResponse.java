package za.co.anycompany.exception;

import java.util.List;

public class ErrorResponse {
    private String error;
    private int code;
    private List<ErrorMessage> errors;

    public ErrorResponse() {
    }

    public ErrorResponse(String error, int code, List<ErrorMessage> errors) {
        this.error = error;
        this.code = code;
        this.errors = errors;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ErrorMessage> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorMessage> errors) {
        this.errors = errors;
    }
}

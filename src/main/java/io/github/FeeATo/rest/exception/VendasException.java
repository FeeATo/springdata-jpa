package io.github.FeeATo.rest.exception;

public class VendasException extends Exception {

    private VendasEnumException exceptionEnum;

    public VendasException(String message) {
        super(message);
        exceptionEnum = VendasEnumException.BAD_REQ;
    }

    public VendasException(String message, Throwable cause) {
        super(message, cause);
        if (cause != null) {
            cause.printStackTrace();
        }
        exceptionEnum = VendasEnumException.INTERNAL_ERROR;
    }

    public VendasException(String message, VendasEnumException exceptionEnum) {
        super(message);
        this.exceptionEnum = exceptionEnum;
    }

    public VendasEnumException getExceptionEnum() {
        return exceptionEnum;
    }

    public void setExceptionEnum(VendasEnumException exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    
}

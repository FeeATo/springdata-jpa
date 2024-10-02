package io.github.FeeATo.rest.exception;

public class VendasException extends Exception {

    private VendasExceptionEnum exceptionEnum;

    public VendasException(String message) {
        super(message);
        exceptionEnum = VendasExceptionEnum.BAD_REQ;
    }

    public VendasException(String message, Throwable cause) {
        super(message, cause);
        exceptionEnum = VendasExceptionEnum.INTERNAL_ERROR;
    }

    public VendasException(String message, VendasExceptionEnum exceptionEnum) {
        super(message);
        this.exceptionEnum = exceptionEnum;
    }

    public VendasExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    public void setExceptionEnum(VendasExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public enum VendasExceptionEnum {
        BAD_REQ,
        INTERNAL_ERROR,
        NOT_FOUND
    }
}

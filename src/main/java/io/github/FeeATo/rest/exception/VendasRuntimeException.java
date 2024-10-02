package io.github.FeeATo.rest.exception;

public class VendasRuntimeException extends RuntimeException {

    private VendasEnumException exceptionEnum;

    public VendasRuntimeException(String message) {
        super(message);
        exceptionEnum = VendasEnumException.BAD_REQ;
    }

    public VendasRuntimeException(String message, Throwable cause) {
        super(message, cause);
        exceptionEnum = VendasEnumException.INTERNAL_ERROR;
    }

    public VendasRuntimeException(String message, VendasEnumException exceptionEnum) {
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

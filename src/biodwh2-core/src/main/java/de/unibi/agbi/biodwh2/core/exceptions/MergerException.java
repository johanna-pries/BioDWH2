package de.unibi.agbi.biodwh2.core.exceptions;

public class MergerException extends Exception {
    public MergerException() {
    }

    public MergerException(String message) {
        super(message);
    }

    public MergerException(String message, Throwable cause) {
        super(message, cause);
    }

    public MergerException(Throwable cause) {
        super(cause);
    }

    public MergerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
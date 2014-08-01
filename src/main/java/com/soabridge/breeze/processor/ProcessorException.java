package com.soabridge.breeze.processor;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class ProcessorException extends Exception {

    public ProcessorException() {
        this(null, null);
    }

    public ProcessorException(String message) {
        this(message, null);
    }

    public ProcessorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessorException(Throwable cause) {
        this(null, cause);
    }

}

package com.soabridge.breeze.processor;

import com.soabridge.breeze.messaging.Message;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class ProcessingException extends Exception {

    private Message payload;

    public ProcessingException(Message payload) {
        this(payload, null, null);
    }

    public ProcessingException(Message payload, String message) {
        this(payload, message, null);
    }

    public ProcessingException(Message payload, String message, Throwable cause) {
        super(message, cause);
        setPayload(payload);
    }

    public ProcessingException(Message payload, Throwable cause) {
        this(payload, null, cause);
    }

    protected void setPayload(Message payload) {
        this.payload = payload;
    }

    public Message getPayload() {
        return this.payload;
    }

}

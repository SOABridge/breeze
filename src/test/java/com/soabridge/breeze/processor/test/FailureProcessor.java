package com.soabridge.breeze.processor.test;

import com.soabridge.breeze.messaging.Message;
import com.soabridge.breeze.processor.Processor;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class FailureProcessor implements Processor {

    private boolean success;

    public FailureProcessor() {
    }

    @Override
    public void onFailure(Message message) {
        this.success = false;
    }

    @Override
    public void onSuccess(Message message) {
        this.success = true;
    }

    @Override
    public Message process(Message message) throws Exception {
        // Throw Exception simulating processing failed
        throw new Exception("This is a test Exception");
    }

    public boolean hasSuccess() {
        return this.success;
    }
}

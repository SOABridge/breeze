package com.soabridge.breeze.processor.test;

import com.soabridge.breeze.messaging.Message;
import com.soabridge.breeze.processor.Processor;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class SuccessProcessor implements Processor {

    private boolean success;

    public SuccessProcessor() {
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
        // Return the message simulating processing succeeded
        return message;
    }

    public boolean hasSuccess() {
        return this.success;
    }
}

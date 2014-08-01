package com.soabridge.breeze.processor;

import com.soabridge.breeze.messaging.Message;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public interface Processor {

    void onFailure(Message message);

    void onSuccess(Message message);

    Message process(Message message);

}

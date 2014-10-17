package org.soabridge.breeze.comm.test;

import org.soabridge.breeze.comm.Endpoint;
import org.soabridge.breeze.messaging.Message;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class SampleEndpoint implements Endpoint {

    private Message lastMessage;

    @Override
    public void deliver(Message message) throws Throwable {
        lastMessage = message;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

}

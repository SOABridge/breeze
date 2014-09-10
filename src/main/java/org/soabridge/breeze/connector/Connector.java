package org.soabridge.breeze.connector;

import org.soabridge.breeze.messaging.Message;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public interface Connector {

    public void deliver(Message message);

}

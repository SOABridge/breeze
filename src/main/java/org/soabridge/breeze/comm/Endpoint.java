package org.soabridge.breeze.comm;

import org.soabridge.breeze.messaging.Message;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public interface Endpoint {

    void deliver(Message message) throws Throwable;

}

package org.soabridge.breeze.connector;

import org.soabridge.breeze.messaging.Message;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class ConnectorWorker implements Runnable {

    private final Connector connector;
    private final ConcurrentLinkedQueue<Message> msgQueue;

    public ConnectorWorker(Connector connector) {
        this.connector = connector;
        this.msgQueue = new ConcurrentLinkedQueue<Message>();
    }

    /**
     * Submits a message for delivery to the end-system. The message might not be delivered immediately but will be
     * placed in a queue of messages that wait for final delivery.
     *
     * @param message The message to be delivered.
     */
    public void submit(Message message) {
        msgQueue.add(message);
    }

    @Override
    public void run() {
    }

}

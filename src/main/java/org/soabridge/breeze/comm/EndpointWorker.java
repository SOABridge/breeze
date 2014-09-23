package org.soabridge.breeze.comm;

import org.soabridge.breeze.messaging.Message;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class EndpointWorker implements Runnable {

    private final Endpoint endpoint;
    private final ConcurrentLinkedQueue<Message> msgQueue;

    public EndpointWorker(Endpoint endpoint) {
        // Check if Endpoint was NULL, throw exception if it was NULL
        Objects.requireNonNull(endpoint, "Endpoint must not be NULL");
        // Assign Endpoint and initialize rest of class
        this.endpoint = endpoint;
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
        notifyAll();
    }

    /**
     * Returns the Endpoint Object for this Worker Class.
     * @return Underlying Endpoint Object.
     */
    public Endpoint getEndpoint() {
        return endpoint;
    }

    /**
     * Schedules the Worker for termination. The Worker might not shut down immediately but will exit it's
     * <code>run()</code> method as soon as possible.
     */
    public void terminate() {
        // TODO: Implement termination mechanism for Worker
    }

    @Override
    public void run() {
        try {
            // Repeat as long as the Thread was not interrupted
            while (!Thread.currentThread().isInterrupted()) {
                while(msgQueue.isEmpty()) {
                    wait();
                }
                // Retrieve message from queue, validate it (right now only checking for NotNull), and deliver it
                Message msg = msgQueue.poll();
                if (msg != null) {
                    endpoint.deliver(msg);
                }
            }
        }
        catch (InterruptedException e) {
            // Not sure if I need to do anything with this exception. For now I leave the catch block empty.
            // Discussion: If the Endpoint is shut down before all messages of the queue are processed should
            //             the remaining messages in the queue be erased or dumped for later delivery?
        }
        finally {
            // TODO: Come up with mechanism to save or discard messages still in the queue.
        }
    }

}

package org.soabridge.breeze.comm;

import org.soabridge.breeze.messaging.Message;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class EndpointWorker implements Runnable {

    public static enum WorkerStatus {
        RUNNABLE,
        RUNNING,
        TERMINATING,
        TERMINATED
    }

    private final Endpoint endpoint;
    private final ConcurrentLinkedQueue<Message> msgQueue;

    private WorkerStatus status;

    private boolean terminated = false;

    public EndpointWorker(Endpoint endpoint) {
        // Check if Endpoint was NULL, throw exception if it was NULL
        Objects.requireNonNull(endpoint, "Endpoint must not be NULL");
        // Assign Endpoint and initialize rest of class
        this.endpoint = endpoint;
        this.msgQueue = new ConcurrentLinkedQueue<Message>();
        // Set Worker status
        this.status = WorkerStatus.RUNNABLE;
    }

    /**
     * Submits a message for delivery to the end-system. The message might not be delivered immediately but will be
     * placed in a queue of messages that wait for final delivery.
     *
     * @param message The message to be delivered.
     */
    public boolean submit(Message message) {
        if (status == WorkerStatus.RUNNABLE || status == WorkerStatus.RUNNING) {
            msgQueue.add(message);
            return true;
        }
        else
            return false;
    }

    /**
     * Returns the Endpoint Object for this Worker Class.
     * @return Underlying Endpoint Object.
     */
    public Endpoint getEndpoint() {
        return endpoint;
    }

    /**
     * Returns the current runtime status of the Worker.
     * @return Runtime status
     */
    public WorkerStatus getStatus() {
        return status;
    }

    /**
     * Schedules the Worker for termination. The Worker might not shut down immediately but will exit it's
     * <code>run()</code> method as soon as possible.
     */
    public void terminate() {
        // Change Worker status to TERMINATING if current status is RUNNING (Worker was already started)
        if (status == WorkerStatus.RUNNING)
            status = WorkerStatus.TERMINATING;
    }

    @Override
    public void run() {
        try {
            // Change Worker status to RUNNING if current status is RUNNABLE (Worker was terminated before it was started)
            if (status == WorkerStatus.RUNNABLE)
                status = WorkerStatus.RUNNING;
            // Repeat as long as the current Thread status is RUNNING
            while (status == WorkerStatus.RUNNING) {
                // If Queue isn't empty process first Message in Queue
                if (!msgQueue.isEmpty()) {
                    // Retrieve message from queue, validate it (right now only checking for NotNull), and deliver it
                    Message msg = msgQueue.poll();
                    if (msg != null) {
                        // Try to deliver Message to Endpoint
                        try {
                            endpoint.deliver(msg);
                        }
                        // Catch all exception the Endpoint might throw and handle them
                        catch (Throwable throwable) {
                            // TODO: Handle failed Endpoint delivery
                        }
                    }
                }
                // Otherwise wait a certain amount of time and then retry
                else {
                    TimeUnit.MILLISECONDS.sleep(1000);
                }
            }
        }
        catch (InterruptedException e) {
            // TODO: Come up with handling of interrupted Workers
            // Not sure if I need to do anything with this exception. For now I leave the catch block empty.
            // Discussion: If the Endpoint is shut down before all messages of the queue are processed should
            //             the remaining messages in the queue be erased or dumped for later delivery?
        }
        finally {
            // TODO: Come up with mechanism to save or discard messages still in the queue.
            status = WorkerStatus.TERMINATED;
        }
    }

}

package org.soabridge.breeze.comm.test;

import org.junit.Test;
import org.soabridge.breeze.comm.EndpointWorker;
import org.soabridge.breeze.messaging.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class EndpointTest {

    @Test
    public void deliveryTest() throws InterruptedException {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        SampleEndpoint endpoint = new SampleEndpoint();
        EndpointWorker worker = new EndpointWorker(endpoint);
        pool.execute(worker);
        // Create input Message
        Message input = new Message();
        // Submit input Message to Worker
        worker.submit(input);
        // Give it some time to be delivered
        TimeUnit.SECONDS.sleep(2);
        // Retrieve last Message from Endpoint
        Message output = endpoint.getLastMessage();
        // Test if input and output Message are equal
        assertThat("Input and output message must be the same!", output, equalTo(input));
    }

    @Test
    public void workerStatusTest() throws InterruptedException {
        EndpointWorker worker = new EndpointWorker(new SampleEndpoint());
        assertThat("Worker must be RUNNABLE before being executed!",
                worker.getStatus(), equalTo(EndpointWorker.WorkerStatus.RUNNABLE));

        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(worker);
        // Give ExecutorService 500ms to start executing Worker
        TimeUnit.MILLISECONDS.sleep(500);
        assertThat("Worker must be RUNNING after being scheduled for execution!",
                worker.getStatus(), equalTo(EndpointWorker.WorkerStatus.RUNNING));

        worker.terminate();
        assertThat("Worker must be TERMINATING after being requested to terminate",
                worker.getStatus(), equalTo(EndpointWorker.WorkerStatus.TERMINATING));

        TimeUnit.SECONDS.sleep(2);
        assertThat("Worker must be TERMINATED not more than 2sec after being terminated",
                worker.getStatus(), equalTo(EndpointWorker.WorkerStatus.TERMINATED));

    }

}

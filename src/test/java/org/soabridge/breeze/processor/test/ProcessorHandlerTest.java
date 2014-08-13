package org.soabridge.breeze.processor.test;

import org.soabridge.breeze.messaging.Message;
import org.soabridge.breeze.processor.ProcessingException;
import org.soabridge.breeze.processor.Processor;
import org.soabridge.breeze.processor.ProcessorException;
import org.soabridge.breeze.processor.ProcessorHandler;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class ProcessorHandlerTest {

    @Test
    public void testCreation() {

        ProcessorHandler handler1 = null;

        try {
            handler1 = new ProcessorHandler(SuccessProcessor.class, FailureProcessor.class);
        }
        catch (ProcessorException e) {
            fail("Expected no Exception while creating ProcessorHandler");
        }

        assertNull("Expected the first ProcessorHandler not to have a Predecessor", handler1.getPredecessor());

        Processor p1 = handler1.getProcessor();
        assertNotNull("Expected first Processor instance to be not NULL", p1);
        assertEquals("Expected first Processor to be of type SuccessProcessor", SuccessProcessor.class, p1.getClass());

        ProcessorHandler handler2 = handler1.getSuccessor();
        assertNotNull("Expected the second ProcessorHandler to be not NULL", handler2);

        Processor p2 = handler2.getProcessor();
        assertEquals("Expected Predecessor of second ProcessorHandler to be first Handler", handler2.getPredecessor(), handler1);
        assertNotNull("Expected second Processor instance to be not NULL", p2);
        assertEquals("Expected second Processor to be of type FailureProcessor", FailureProcessor.class, p2.getClass());
        assertNull("Expected the second ProcessorHandler not to have a Successor", handler2.getSuccessor());
    }

    @Test
    public void testNegative() {
        Message msgIn = new Message();
        try {
            ProcessorHandler handler = new ProcessorHandler(SuccessProcessor.class, FailureProcessor.class);
            handler.process(msgIn);
            fail("Expected processing to fail");
        }
        catch (ProcessingException e) {
            assertNotNull("Expected the message payload of Exception to be a non-NULL value", e.getPayload());
            assertEquals("Expected IN message and Exception payload to be the same", msgIn, e.getPayload());
        }
        catch (ProcessorException e) {
            fail("Expected no Exception while creating ProcessorHandler");
        }
    }

    @Test
    public void testPositive() {
        Message msgIn = new Message();
        try {
            ProcessorHandler handler = new ProcessorHandler(SuccessProcessor.class, SuccessProcessor.class);
            Message msgOut = handler.process(msgIn);
            assertNotNull("Expected non-Null return message", msgIn);
            assertEquals("Expected IN message and OUT message to be the same", msgIn, msgOut);
        }
        catch (ProcessingException e) {
            fail("Expected no Exception while processing Message");
        }
        catch (ProcessorException e) {
            fail("Expected no Exception while creating ProcessorHandler");
        }
    }

}

package com.soabridge.breeze.processor;

import com.soabridge.breeze.messaging.Message;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class ProcessorHandler {

    private Processor processor;

    private ProcessorHandler predecessor;

    private ProcessorHandler successor;

    public ProcessorHandler(Class<? extends Processor>... processors) throws ProcessorException {
        // Check if 'processors' is NULL
        Objects.requireNonNull(processors, "The specified processors must not be Null");
        // Check if 'processors' is empty array
        if (processors.length <= 0) throw new IllegalArgumentException("At least one processor must be specified");
        // Create a new instance of the Processor and set it as local instance
        Class<? extends Processor> processorClass = processors[0];
        try {
            setProcessor(processorClass.newInstance());
        }
        catch (Exception e) {
            throw new ProcessorException("Processor '" + processorClass + "' could not be instantiated", e);
        }
        // Check if more than one processor is remaining
        if (processors.length > 1) {
            setSuccessor(new ProcessorHandler(Arrays.copyOfRange(processors, 1, processors.length)));
            getSuccessor().setPredecessor(this);
        }
    }

    protected void onFailure(Message message){
        getProcessor().onFailure(message);
    }

    protected void onSuccess(Message message) {
        getProcessor().onSuccess(message);
    }

    public Message process(Message message) throws ProcessingException {
        Message internalMsg = message;
        try {
            // Call process() on local Processor instance
            internalMsg = getProcessor().process(internalMsg);
            // Call subsequent ProcessorHandlers if there are any
            if (getSuccessor() != null)
                internalMsg = getSuccessor().process(internalMsg);
            // Everything processed well now call onSuccess()
            onSuccess(internalMsg);
            // Return Message
            return internalMsg;
        }
        // Handle ProcessingException thrown by one of the successors
        catch (ProcessingException pe) {
            onFailure(pe.getPayload());
            throw pe;
        }
        // Handle exceptions thrown by the local processor instance
        catch (Throwable t) {
            onFailure(internalMsg);
            throw new ProcessingException(internalMsg, t);
        }
    }

    public ProcessorHandler getPredecessor() {
        return this.predecessor;
    }

    protected void setPredecessor(ProcessorHandler handler) {
        this.predecessor = handler;
    }

    public Processor getProcessor() {
        return this.processor;
    }

    protected void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public ProcessorHandler getSuccessor() {
        return this.successor;
    }

    protected void setSuccessor(ProcessorHandler handler) {
        this.successor = handler;
    }

}

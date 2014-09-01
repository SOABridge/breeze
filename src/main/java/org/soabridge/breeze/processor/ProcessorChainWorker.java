package org.soabridge.breeze.processor;

import org.soabridge.breeze.messaging.Message;

import java.util.logging.Logger;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class ProcessorChainWorker implements Runnable {

    private static Logger logger = Logger.getLogger(ProcessorChainWorker.class.toGenericString());

    private Message message;


    public ProcessorChainWorker(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        try {
            logger.finer("Starting to process message " + message.getId());
            // TODO: Read current Stage and Stage Pipeline from Message
            Class<? extends Processor>[] processes = new Class[0];

            // TODO: Build ProcessorHandler chain
            ProcessorHandler handler = new ProcessorHandler(processes);

            // TODO: Tell ProcessorHandler to process Message
            Message result = handler.process(message);

            // TODO: Handle processing output
        }
        catch (ProcessorException | ProcessingException e) {
            // TODO: Handle failed processing (Idea: Have HiveFaultHandler)
        }
    }
}

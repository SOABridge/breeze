package com.soabridge.breeze.processor;

import java.util.logging.Logger;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class ProcessorChainWorker implements Runnable {

    private static Logger logger = Logger.getLogger(ProcessorChainWorker.class.toGenericString());

    private String message;


    public ProcessorChainWorker() {
    }

    @Override
    public void run() {
    }

    public synchronized void processMessage(String message) {
        this.message = message;
        this.notify();
    }
}

package org.soabridge.breeze;

import org.soabridge.breeze.connector.Connector;
import org.soabridge.breeze.connector.ConnectorWorker;
import org.soabridge.breeze.registry.Token;
import org.soabridge.breeze.registry.TokenFactory;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public final class Hive {

    private final ExecutorService poolConnectors;

    private final HashMap<Token,ConnectorWorker> regConnectors;

    public Hive() {
        // TODO: Make ThreadPool size configurable
        poolConnectors = Executors.newFixedThreadPool(50);

        regConnectors = new HashMap<>();
    }

    /**
     *
     * @param connector
     * @return
     */
    public Token<Connector> registerConnector(Connector connector) {
        return registerConnector(null, connector);
    }

    /**
     *
     * @param token
     * @param connector
     * @return
     */
    public Token<Connector> registerConnector(Token<Connector> token, Connector connector) {
        // Check if connector is NULL, throw NullPointer if so.
        Objects.requireNonNull(connector, "NULL cannot be registered as Connector!");
        // Check if the provided Token was Null, if so generate a new one
        Token<Connector> regToken = (token != null)? token : TokenFactory.generateConnectorToken();
        // If Token not already registered...
        if(!regConnectors.containsKey(regToken)) {
            // ...create new Worker for Connector
            ConnectorWorker worker = new ConnectorWorker(connector);
            // ...hand it over for execution to the ThreadPool
            poolConnectors.execute(worker);
            // ...register Worker in Connector Registry
            regConnectors.put(regToken, worker);
            return regToken;
        }
        // Otherwise return NULL indicating that the token is already registered.
        else
            return null;
    }

    /**
     *
     * @param token
     * @return
     */
    public Connector unregisterConnector(Token<Connector> token) {
        // Return NULL if the provided token was null
        if (token == null)
            return null;
        // If the Token is currently registered...
        if (regConnectors.containsKey(token)) {
            // ...remove the Token and corresponding Worker from the Registry
            ConnectorWorker worker = regConnectors.remove(token);
            // ...schedule the Worker for Termination
            worker.terminate();
            //...return the Connector Object of the Worker
            return worker.getConnector();
        }
        // Otherwise return NULL if the provided Token was not found in Registry
        else
            return null;
    }

}

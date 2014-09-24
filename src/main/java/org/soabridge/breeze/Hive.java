package org.soabridge.breeze;

import org.soabridge.breeze.comm.Endpoint;
import org.soabridge.breeze.comm.EndpointWorker;
import org.soabridge.breeze.registry.RegistrationException;
import org.soabridge.breeze.registry.Token;
import org.soabridge.breeze.registry.TokenFactory;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public final class Hive {

    private final ExecutorService poolEndpoint;

    private final HashMap<Token,EndpointWorker> regEndpoints;

    public Hive() {
        // TODO: Make ThreadPool size configurable
        poolEndpoint = Executors.newFixedThreadPool(50);

        regEndpoints = new HashMap<Token, EndpointWorker>();
    }

    /**
     *
     * @param endpoint
     * @return
     */
    public Token<Endpoint> registerEndpoint(Endpoint endpoint) throws RegistrationException {
        return registerEndpoint(null, endpoint);
    }

    /**
     *
     * @param token
     * @param endpoint
     * @return
     */
    public Token<Endpoint> registerEndpoint(Token<Endpoint> token, Endpoint endpoint) throws RegistrationException {
        // Check if provided Endpoint is NULL
        if (endpoint == null)
            throw new RegistrationException("Invalid Endpoint Token: NULL");
        // Check if the provided Token was Null, if so generate a new one
        Token<Endpoint> regToken = (token != null)? token : TokenFactory.generateEndpointToken();
        // If Token not already registered...
        if(!regEndpoints.containsKey(regToken)) {
            // ...create new Worker for Endpoint
            EndpointWorker worker = new EndpointWorker(endpoint);
            // ...hand it over for execution to the ThreadPool
            poolEndpoint.execute(worker);
            // ...register Worker in Endpoint Registry
            regEndpoints.put(regToken, worker);
            return regToken;
        }
        // Otherwise return NULL indicating that the token is already registered.
        else
            throw new RegistrationException("Duplicate registration Token");
    }

    /**
     *
     * @param token
     * @return
     */
    public Endpoint unregisterEndpoint(Token<Endpoint> token) throws RegistrationException {
        // Check if the provided token is NULL
        if (token == null)
            throw new RegistrationException("Invalid registration Token: NULL");
        // If the Token is currently registered...
        if (regEndpoints.containsKey(token)) {
            // ...remove the Token and corresponding Worker from the Registry
            EndpointWorker worker = regEndpoints.remove(token);
            // ...schedule the Worker for Termination
            worker.terminate();
            //...return the Endpoint Object of the Worker
            return worker.getEndpoint();
        }
        // Otherwise return NULL if the provided Token was not found in Registry
        else
            return null;
    }

}

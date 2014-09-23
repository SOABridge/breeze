package org.soabridge.breeze.registry;

import org.soabridge.breeze.comm.Endpoint;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class TokenFactory {

    public static Token<Endpoint> generateEndpointToken() {
        return new DefaultToken<Endpoint>(Endpoint.class);
    }

}

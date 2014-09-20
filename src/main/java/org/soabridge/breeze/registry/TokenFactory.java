package org.soabridge.breeze.registry;

import org.soabridge.breeze.connector.Connector;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class TokenFactory {

    public static Token<Connector> generateConnectorToken() {
        return new DefaultToken<Connector>(Connector.class);
    }

}

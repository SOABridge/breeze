package org.soabridge.breeze.registry.test;

import org.junit.Test;
import org.soabridge.breeze.comm.Endpoint;
import org.soabridge.breeze.registry.Token;
import org.soabridge.breeze.registry.TokenFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class TokenTest {

    @Test
    public void testEquality() {
        Token<Endpoint> token1 = TokenFactory.generateEndpointToken(SampleEndpoint.class);
        Token<Endpoint> token2 = TokenFactory.generateEndpointToken(SampleEndpoint.class);
        Token<Endpoint> token3 = new ClonedEndpointToken(token1);

        assertThat("Token must be equal to itself!", token1, equalTo(token1));
        assertThat("Token must not be equal to newly created Token!", token1, not(equalTo(token2)));
        assertThat("Token must be equal token with same values!", token1, equalTo(token3));
    }

    public void testHashcode() {
        Token<Endpoint> token1 = TokenFactory.generateEndpointToken(SampleEndpoint.class);
        Token<Endpoint> token2 = TokenFactory.generateEndpointToken(SampleEndpoint.class);
        Token<Endpoint> token3 = new ClonedEndpointToken(token1);

        assertThat("Token hash code must be equal to itself!"
                , token1.hashCode(), equalTo(token1.hashCode()));
        assertThat("Token hash code must not be equal to other Token hash code!"
                , token1.hashCode(), not(equalTo(token2.hashCode())));
        assertThat("Token hash code must be equal token Token hash code with same values!"
                , token1.hashCode(), equalTo(token3.hashCode()));
    }
}
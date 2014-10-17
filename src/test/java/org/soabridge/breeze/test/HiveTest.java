package org.soabridge.breeze.test;

import org.junit.Test;
import org.soabridge.breeze.Hive;
import org.soabridge.breeze.comm.Endpoint;
import org.soabridge.breeze.registry.RegistrationException;
import org.soabridge.breeze.registry.Token;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Missing documentation.
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
public class HiveTest {

    @Test
    public void registrationTest() {
        Hive hive = new Hive();
        TestEndpoint endpoint = new TestEndpoint();
        Token<Endpoint> token = null;

        // Test normal Endpoint registration
        try {
            token = hive.registerEndpoint(endpoint);
            assertThat("Registration Token must not be NULL",
                    token, notNullValue());
            assertThat("Registration Token must be of type Endpoint.",
                    token.getType(), equalTo(Endpoint.class));
            assertThat("Registration Token implementation be of TestEndpoint.",
                    token.getImplementation(), equalTo(TestEndpoint.class));
        }
        catch (RegistrationException e) {
            fail("No Exception expected during regular Endpoint registration.");
        }

        // Test normal Endpoint un-registration
        try {
            Endpoint resultEndp = hive.unregisterEndpoint(token);
            assertThat("Endpoint returned during un-registration must not be NULL.",
                    resultEndp, notNullValue());
            assertThat("Unregistered Endpoint must be equal to returned Endpoint during un-registration.",
                    resultEndp, equalTo(endpoint));
        }
        catch (RegistrationException e) {
            fail("No Exception expected during regular Endpoint un-registration.");
        }

        // Test un-registration of already un-registered Endpoint
        try {
            Endpoint resultEndp = hive.unregisterEndpoint(token);
            assertThat("Endpoint returned during 2nd un-registration mut be NULL",
                    resultEndp, nullValue());
        }
        catch (RegistrationException e) {
            fail("No Exception expected during un-registration of already un-registered Endpoint.");
        }

        // Test normal Endpoint re-registration
        try {
            Token<Endpoint> resultToken = hive.registerEndpoint(token, endpoint);
            assertThat("Token returned during re-registration must not be null.",
                    resultToken, notNullValue());
            assertThat("Token returned during re-registration must be the same as provided token.",
                    resultToken, equalTo(token));
        }
        catch (RegistrationException e) {
            fail("No Exception expected during regular Endpoint re-registration.");
        }

        // Test duplicate Endpoint re-registration
        try {
            hive.registerEndpoint(token, endpoint);
            fail("Registration of an Endpoint with an existing Token is not expected to succeed");
        }
        catch (RegistrationException e) {
            // Nothing to do here, registration is expected to fail
        }

        // Test NULL Endpoint registration
        try {
            hive.registerEndpoint(null);
            fail("Registration of NULL as Endpoint is not expected to succeed.");
        }
        catch (RegistrationException e) {
            // Nothing to do here, registration of NULL is expected to fail
        }

        // Test NULL Token un-registration
        try {
            hive.unregisterEndpoint(null);
            fail("Un-registration of a NULL Token is not expected to succeed.");
        }
        catch (RegistrationException e) {
            // Nothing to do here, un-registration of NULL is expected to fail
        }
    }

}

package org.soabridge.breeze.messaging;

/**
 * Message property types
 * @author <a href="divyesh.vallabh@soabridge.com">Divyesh Vallabh</a>
 * @since 1.0
 *
 */
public class Property {

    /**
     * Default keys available in the body of the message
     */
    public enum Body
    {
        ORIGINAL_PAYLOAD,
        PAYLOAD
    };

    /**
     * Property keys available in the header of the message
     */
    public enum Header
    {
        DESTINATION,
        REFERENCE_MESSAGE_ID,
        SOURCE
    };

    /**
     * Property scopes of a message
     */
    public enum Scope
    {
        BODY,
        HEADER,
        SESSION
    }

    /**
     * Property keys available in the session of the message
     */
    public enum Session
    {
        ACTION_PIPELINES
    }
}

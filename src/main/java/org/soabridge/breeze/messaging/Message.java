package org.soabridge.breeze.messaging;

import java.util.UUID;

/**
 * Message thats passing through for processing
 * @author <a href="divyesh.vallabh@soabridge.com">Divyesh Vallabh</a>
 * @since 1.0
 *
 */
public class Message {

    private Body body;
    private String id;
    private Header header;

    /**
     * Default constructor initializing properties of the message
     */
    public Message()
    {
        //assign a UUID number to the message
        id = UUID.randomUUID().toString();
        body = new Body();
        header = new Header();
    }

    /**
     * Gets body properties of the message
     * @return {@link Body} : Body properties of the message
     */
    public Body getBody() {
        return body;
    }

    /**
     * Unique identifier of the message
     * @return String : Unique identifier of the message
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the header properties of the message
     * @return {@link Header} : Header properties of the message
     */
    public Header getHeader() {
        return header;
    }

}

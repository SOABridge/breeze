package org.soabridge.breeze.messaging;

/**
 * Message body properties
 * @author <a href="divyesh.vallabh@soabridge.com">Divyesh Vallabh</a>
 * @since 1.0
 *
 */
public class Body extends Data {

    //default key of where the property is stored
    public final static String DEFAULT_PAYLOAD = "DEFAULT_PAYLOAD";

    /**
     * Gets the default payload of the message
     * @return Object : Default payload of the message
     */
    public Object getPayload()
    {
        return getProperties().get(DEFAULT_PAYLOAD);
    }

    /**
     * Sets the default payload of the messge
     * @param payload : Default payload of the message
     */
    public void setPayload(Object payload)
    {
        getProperties().put(DEFAULT_PAYLOAD, payload);
    }

}

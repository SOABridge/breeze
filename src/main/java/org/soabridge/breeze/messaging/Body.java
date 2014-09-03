package org.soabridge.breeze.messaging;

/**
 * Message body properties
 * @author <a href="divyesh.vallabh@soabridge.com">Divyesh Vallabh</a>
 * @since 1.0
 *
 */
public class Body extends Data {

    public Body()
    {
        super();
    }

    public Body(Body body)
    {
        this();
        if (body != null)
            this.properties.putAll(body.getProperties());
    }


    /**
     * Gets the default payload of the message
     * @return Object : Default payload of the message
     */
    public Object getPayload()
    {
        return getProperty(Property.Body.PAYLOAD.toString());
    }

    /**
     * Sets the default payload of the messge
     * @param payload : Default payload of the message
     */
    public void setPayload(Object payload)
    {
        setProperty(Property.Body.PAYLOAD.toString(), payload);
    }

}

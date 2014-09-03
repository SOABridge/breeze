package org.soabridge.breeze.messaging;

/**
 * Message header properties
 * @author <a href="divyesh.vallabh@soabridge.com">Divyesh Vallabh</a>
 * @since 1.0
 *
 */
public class Header extends Data {

    /**
     * Default header constructor
     */
    public Header() { this(null, null); }

    /**
     * Default initializing properties of header source and destination
     * @param source: where the message is from
     * @param destination: where the message is been delivered to
     */
    public Header(String source, String destination)
    {
        super();
        setProperty(Property.Header.SOURCE.toString(), source);
        setProperty(Property.Header.DESTINATION.toString(), destination);
    }

    public Header(Header header)
    {
        super();
        if (header != null)
            this.properties.putAll(header.getProperties());
    }

    /**
     * Gets the destination of the message
     * @return String : Unique identifier of where the message is been delivered to
     */
    public String getDestination() {
        return (String)getProperty(Property.Header.DESTINATION.toString());
    }

    /**
     * Gets the reference message id
     * @return String : reference message id
     */
    public String getReferenceMessageId() { return (String)getProperty(Property.Header.REFERENCE_MESSAGE_ID.toString()); }

    /**
     * Gets where the message came from
     * @return String : Unique identifier of where the message came from
     */
    public String getSource() { return (String)getProperty(Property.Header.SOURCE.toString()); }



    /**
     * Sets where the message is been delivered to
     * @param destination : Where the message is been delivered to
     */
    public void setDestination(String destination) {
        setProperty(Property.Header.DESTINATION.toString(), destination);
    }

    /**
     * Sets the reference message id
     * @param messageId : reference message id
     */
    public void setReferenceMessageId(String messageId) { setProperty(Property.Header.REFERENCE_MESSAGE_ID.toString(), messageId); }

    /**
     * Sets where the message came from
     * @param source : Where the message came from
     */
    public void setSource(String source) { setProperty(Property.Header.SOURCE.toString(), source); }
}

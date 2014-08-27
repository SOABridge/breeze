package org.soabridge.breeze.messaging;

/**
 * Message header properties
 * @author <a href="divyesh.vallabh@soabridge.com">Divyesh Vallabh</a>
 * @since 1.0
 *
 */
public class Header {

    private String from;
    private Data properties;
    private String to;

    /**
     * Default constructor initializing properties of the header
     */
    public Header()
    {
        properties = new Data();
    }

    /**
     * Where the message came from
     * @return String : Unique identifier of where the message came from
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets where the message came from
     * @param from : Where the message came from
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Gets the header properties
     * @return {@link Data} : Header properties of the header
     */
    public Data getProperties() {
        return properties;
    }

    /**
     * Gets the destination of the message
     * @return String : Unique identifier of where the message is heading to
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets where the message is going to
     * @param to : Where the message came to
     */
    public void setTo(String to) {
        this.to = to;
    }


}

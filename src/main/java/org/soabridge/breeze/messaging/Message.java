package org.soabridge.breeze.messaging;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * Message that's passing through for processing
 * @author <a href="divyesh.vallabh@soabridge.com">Divyesh Vallabh</a>
 * @since 1.0
 *
 */
public class Message implements Serializable {

    private Body body;
    private String id;
    private Header header;
    private Session session;

    /**
     * Default constructor initializing properties of the message
     */
    public Message()
    {
        this(null,null, new DefaultActionPipelineConfiguration());
    }

    /**
     * Default constructor initializing properties of the message
     * @param header : message header information
     */
    public Message(Header header, Body body)
    {
        this(header, body, new DefaultActionPipelineConfiguration());
    }

    /**
     * Construct with a referenced Message id
     * @param header : : message header information
     * @param actionPipelineConfiguration : action pipeline to execute
     */
    public Message(Header header, Body body, ActionPipelineConfiguration actionPipelineConfiguration)
    {
        //check to see if the phase configuration is null
        if(actionPipelineConfiguration == null)
            throw new IllegalArgumentException("Phase configuration cannot be null!");

        //ensure no null values exist in the phase configurations
        if (actionPipelineConfiguration.getPipeline().stream().filter(actionPipeline -> actionPipeline == null).count() > 0)
            throw new IllegalArgumentException("Pipeline configuration cannot contain nulls!");

        //assign a UUID number to the message
        this.id = UUID.randomUUID().toString();
        this.body = new Body(body);
        this.header = new Header(header);
        this.session = new Session();

        //sets the phase configuration of message
        session.setActionPipelineConfiguration(actionPipelineConfiguration);
    }

    /**
     * Gets the action pipelines
     * @return IActionPipelineConfiguration : action pipeline configuration
     */
    public final ActionPipelineConfiguration getActionPipelineConfiguration() {
        return session.getActionPipelineConfiguration();
    }

    /**
     * Gets property value from body of message
     * @param property : name of property from body of message
     * @return Object : Body properties of the message
     */
    public <T> T getBodyProperty(String property) { return this.body.getProperty(property); }


    /**
     * Gets where the message is been delivered to
     * @return String : where the message is been delivered to
     */
    public String getDestination() { return this.header.getDestination(); }

    /**
     * Gets property value from header of message
     * @param property : name of property from header
     * @return Object : Header properties of the message
     */
    public <T> T getHeaderProperty(String property) { return this.header.getProperty(property); }

    /**
     * Unique identifier of the message
     * @return String : Unique identifier of the message
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gets message payload
     * @return Object : gets message payload
     */
    public <T> T getPayload() {
        return (T)this.body.getPayload();
    }

    /**
     * Gets the reference message id
     * @return String : reference message id
     */
    public String getReferenceMessageId() {
        return this.header.getReferenceMessageId();
    }

    /**
     * Returns list of properties from specified scope
     * @param scope : scope from which to retrieve list of properties
     * @return Set<String> : list of property names
     */
    public Set<String> getPropertyNames(Property.Scope scope)
    {
        Set<String> keys = null;

        //gets the list of properties from
        switch (scope)
        {
            case HEADER: keys = this.header.getProperties().keySet(); break;
            case SESSION: keys =  this.session.getProperties().keySet(); break;
            default: keys = this.body.getProperties().keySet(); break;
        }

        return keys;
    }

    /**
     * Gets the session data properties of the message
     * @param property : get property value from meta of message
     * @return Object : value property in session
     */
    public <T> T getSessionProperty(String property) { return this.session.getProperty(property); }

    /**
     * Gets where the message came from
     * @return String : where the message came from
     */
    public String getSource() { return this.header.getSource(); }

    /**
     * Removes property from body of message
     * @param property : property name
     */
    public void removeProperty(String property) {
        removeProperty(property, Property.Scope.BODY);
    }

    /**
     * Removes all property from message from each scope
     */
    public void removeAllProperties(Property.Scope scope) {

        switch (scope) {
            case HEADER:
                this.header.getProperties().clear();
                break;
            case SESSION:
                this.session.getProperties().clear();
                break;
            default:
                this.body.getProperties().clear();
                break;
        }
    }

    /**
     * Removes property from message
     * @param property : property name
     */
    public void removeProperty(String property, Property.Scope scope) {

            switch (scope) {
                case HEADER:
                    this.header.removeProperty(property);
                    break;
                case SESSION:
                    this.session.removeProperty(property);
                    break;
                default:
                    this.body.removeProperty(property);
                    break;
            }
    }

    /**
     * Sets property value from body of message
     * @param property : name of property to set in body of message
     * @param value : value of property
     */
    public void setBodyProperty(String property, Object value) { this.body.setProperty(property,value); }

    /**
     * Sets where the message is been delivered to
     * @param destination : where the message is been delivered to
     */
    public void setDestination(String destination) { this.header.setDestination(destination); }

    /**
     * Sets where the message payload
     * @param payload : message payload
     */
    public void setPayload(String payload) { this.body.setPayload(payload); }


    /**
     * Sets property value from header of message
     * @param property : name of property to set in header of message
     * @param value : value of property
     */
    public void setHeaderProperty(String property, Object value) { this.header.setProperty(property,value); }

    /**
     * Sets message reference id
     * @param messageId : reference message id
     */
    public void setReferenceMessageId(String messageId) { this.header.setReferenceMessageId(messageId); }


    /**
     * Sets property value from session of message
     * @param property : name of property to set in session of message
     * @param value : value of property
     */
    public void setSessionProperty(String property, Object value) { this.session.setProperty(property,value); }


    /**
     * Sets where the message came from
     * @param source : where the message came from
     */
    public void setSource(String source) { this.header.setSource(source); }

}

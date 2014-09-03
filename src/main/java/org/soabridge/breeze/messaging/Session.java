package org.soabridge.breeze.messaging;

/**
 * Message session data properties
 * @author <a href="divyesh.vallabh@soabridge.com">Divyesh Vallabh</a>
 * @since 1.0
 *
 */
public class Session extends  Data {

    /**
     * Gets the action pipeline of the message
     * @return {@ActionPipelineConfiguration} : action pipeline of the message
     */
    public ActionPipelineConfiguration getActionPipelineConfiguration() {
        return getProperty(Property.Session.ACTION_PIPELINES.toString());
    }

    /**
     * Sets the action pipeline configuration of the message
     * @param actionPipelineConfiguration : action pipeline configuration
     */
    public void setActionPipelineConfiguration(ActionPipelineConfiguration actionPipelineConfiguration) {
        setProperty(Property.Session.ACTION_PIPELINES.toString(), actionPipelineConfiguration);
    }
}

package org.soabridge.breeze.messaging;

import java.io.Serializable;
import java.util.List;

/**
 * Defines action pipeline configuration
 * @author <a href="divyesh.vallabh@soabridge.com">Divyesh Vallabh</a>
 * @since 1.0
 *
 */
public interface ActionPipelineConfiguration extends Serializable {

    public String getFirstPipeline();

    public String getLastPipeline();

    public String getNextPipeline(String currentPipeline);

    public List<String> getPipeline();

}

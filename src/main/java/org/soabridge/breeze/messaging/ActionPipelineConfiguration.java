package org.soabridge.breeze.messaging;

import java.io.Serializable;
import java.util.Set;

/**
 * Defines action pipeline configuration
 * @author <a href="divyesh.vallabh@soabridge.com">Divyesh Vallabh</a>
 * @since 1.0
 *
 */
public interface ActionPipelineConfiguration extends Serializable {

    public Set<String> getPipeline();

}

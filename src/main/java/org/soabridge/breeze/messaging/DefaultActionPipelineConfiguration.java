package org.soabridge.breeze.messaging;

import java.util.HashSet;
import java.util.Set;

/**
 * Default action pipeline configuration for breeze
 * @author <a href="divyesh.vallabh@soabridge.com">Divyesh Vallabh</a>
 * @since 1.0
 *
 */
public class DefaultActionPipelineConfiguration implements ActionPipelineConfiguration {

    /**
     * Name of the default phases
     */
    public enum Pipleline {
        PHASE1,
        PHASE2,
        PHASE3;
    }

    private Set<String> pipleline;

    /**
     * Sets the default phases for breeze
     */
    public DefaultActionPipelineConfiguration()
    {
        pipleline = new HashSet<String>();
        for (Pipleline phase : Pipleline.values())
            pipleline.add(phase.toString());
    }

    /**
     * Gets the list of pipe lines to be configured
     * @return Set<String> : pipe line to be configured
     */
    @Override
    public Set<String> getPipeline() {
        return pipleline;
    }
}

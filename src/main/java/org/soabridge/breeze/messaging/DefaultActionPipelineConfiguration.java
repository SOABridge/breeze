package org.soabridge.breeze.messaging;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
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

    private List<String> pipleline;

    /**
     * Sets the default phases for breeze
     */
    public DefaultActionPipelineConfiguration()
    {
        pipleline = new ArrayList<String>();
        for (Pipleline phase : Pipleline.values())
            pipleline.add(phase.toString());
    }

    @Override
    public String getFirstPipeline() {
        return Pipleline.PHASE1.toString();
    }

    @Override
    public String getLastPipeline() {
        return Pipleline.PHASE3.toString();
    }

    @Override
    public String getNextPipeline(String currentPipeline) {

        String nextPipeline = null;

        //if empty, then regturn the first value in the pipeline
        if (StringUtils.isBlank(currentPipeline))
            nextPipeline =  Pipleline.PHASE1.toString();
        else if (Pipleline.PHASE1.name().equals(currentPipeline))
            nextPipeline = Pipleline.PHASE2.toString();
        else if (Pipleline.PHASE2.name().equals(currentPipeline))
            nextPipeline = Pipleline.PHASE3.toString();
        else if (Pipleline.PHASE3.name().equals(currentPipeline))
            nextPipeline = null;
        else
            throw new IllegalArgumentException("Cannot determine next phase in pipeline");

        return nextPipeline;
    }


    /**
     * Gets the list of pipe lines to be configured
     * @return Set<String> : pipe line to be configured
     */
    @Override
    public List<String> getPipeline() {
        return pipleline;
    }


}

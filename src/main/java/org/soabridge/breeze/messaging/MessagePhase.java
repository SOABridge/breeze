package org.soabridge.breeze.messaging;

/**
 * Message body properties
 * @author <a href="divyesh.vallabh@soabridge.com">Divyesh Vallabh</a>
 * @since 1.0
 *
 */
public enum MessagePhase {

    PHASE_ONE("PRE-PROCESSING"),
    PHASE_TWO("SOLUTION"),
    PHASE_THREE("POST-PROCESSING");

    private String description;

    MessagePhase(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return this.description;
    }

}

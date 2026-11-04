package com.pasdm.integration.dto;

import com.pasdm.integration.enums.ExecutionStatus;
import com.pasdm.integration.enums.TriggerType;
import lombok.Data;

@Data
public class ExecuteRequestDTO {
    private Long sourceConfigurationId;
    private ExecutionStatus executionStatus;
    private TriggerType triggerType;
}

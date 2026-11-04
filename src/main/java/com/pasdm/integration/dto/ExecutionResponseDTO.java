package com.pasdm.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExecutionResponseDTO {
    private Long executionId;
    private String status;
    private String message;
}

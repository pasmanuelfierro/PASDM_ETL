package com.pasdm.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecutionResponseDTO {
    @JsonProperty("id")
    private Long Id;
    private String status;
    private String message;
}

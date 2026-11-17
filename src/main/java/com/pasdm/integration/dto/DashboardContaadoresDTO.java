package com.pasdm.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DashboardContaadoresDTO {
    private Long totalExecutions;
    private Long successfulExecutions;
    private Long failedExecutions;
    private Long runningExecutions;
    private Long totalRecordsRead;
    private Long totalRecordsInserted;
    private Long totalRecordsUpdated;
}

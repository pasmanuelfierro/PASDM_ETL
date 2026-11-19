package com.pasdm.integration.repository;

import com.pasdm.integration.model.TaskExecution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface TaskExecutionRepository extends JpaRepository<TaskExecution,Long> {
    List<TaskExecution> findAllByOrderByStartedAtDesc();

  //  Page<TaskExecution> findByFinishedAt(OffsetDateTime finisheAt, Pageable pageable);

    List<TaskExecution> findByTriggerTypeIgnoreCase(String triggerType);

    Optional<TaskExecution>
    findFirstBySourceConfiguration_IdOrderByStartedAtDescIdDesc(
            Long sourceConfigurationId
    );



    @Query(value = """
        SELECT
            COUNT(*) AS totalExecutions,

            COUNT(*) FILTER (WHERE status = 'SUCCESS') AS successfulExecutions,

            COUNT(*) FILTER (WHERE status IN ('ERROR', 'FAILED', 'FAILURE')) AS failedExecutions,

            COUNT(*) FILTER (WHERE status IN ('RUNNING', 'PENDING')) AS runningExecutions,

            COALESCE(SUM(CAST(NULLIF(records_read, 0) AS BIGINT)), 0) AS totalRecordsRead,

            COALESCE(SUM(CAST(NULLIF(records_inserted, 0) AS BIGINT)), 0) AS totalRecordsInserted,

            COALESCE(SUM(CAST(NULLIF(records_updated, 0) AS BIGINT)), 0) AS totalRecordsUpdated

        FROM manager.task_execution
        """, nativeQuery = true)
    Map<String, Object> getDashboardContadores();

}

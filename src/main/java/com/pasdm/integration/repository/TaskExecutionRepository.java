package com.pasdm.integration.repository;

import com.pasdm.integration.model.TaskExecution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface TaskExecutionRepository extends JpaRepository<TaskExecution,Long> {
    List<TaskExecution> findAllByOrderByStartedAtDesc();

  //  Page<TaskExecution> findByFinishedAt(OffsetDateTime finisheAt, Pageable pageable);

    List<TaskExecution> findByTriggerTypeIgnoreCase(String triggerType);
}

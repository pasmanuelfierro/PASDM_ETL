package com.pasdm.integration.controller;

import com.pasdm.integration.dto.*;
import com.pasdm.integration.model.TaskExecution;
import com.pasdm.integration.repository.TaskExecutionRepository;
import com.pasdm.integration.service.TaskExecutionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@RestController
@RequestMapping("/api/etl/task-execution")
public class TaskExecutionController {

    @Autowired
    private TaskExecutionRepository taskExecutionRepository;

    @Autowired
    private TaskExecutionService taskExecutionService;

    @GetMapping
    public List<TaskExecutionDTO> getTaskExecution() {
        return taskExecutionService.getTaskExecution();
    }

  /*  @GetMapping("/{id}")
    public TaskExecution findById(@PathVariable Long id) {
        return taskExecutionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TaskExecution not found with id " + id));
    }

   */


    @GetMapping("/executions/{id}")
    public ResponseEntity<ExecutionDetailDTO> getExecutionById(@PathVariable Long id) {
        return ResponseEntity.ok(taskExecutionService.getExecutionById(id));
    }

    @GetMapping("/configurations/{configurationId}/lastest-execution")
    public ResponseEntity<LatestExecutionDTO> getLatestExecution(@PathVariable Long configurationId) {
        // log.info("Consultando última ejecución para configuración: {}", configurationId);

        LatestExecutionDTO response = taskExecutionService.getLatestExecution(configurationId);
        //log.info("Resultado de última ejecución: {}", response);

        if (response == null) {
            log.warn("No se encontró ejecución para configuración: {}", configurationId);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    // CONTADORES
    @GetMapping("/dashboard/contadores")
    public ResponseEntity<DashboardContaadoresDTO> getDashboardContadores() {
        DashboardContaadoresDTO response = taskExecutionService.getDashboardContadores();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/origin/{triggerType}")
    public ResponseEntity<List<TaskExecutionDTO>> getTaskExecutionOrigin(@PathVariable String triggerType) {
        return ResponseEntity.ok(taskExecutionService.getTaskExecutionOrigin(triggerType));
    }

    @PostMapping
    public ResponseEntity<TaskExecutionResponseDTO> createTaskExecution(@RequestBody TaskExecutionRequestDTO request) {
        TaskExecution saved = taskExecutionService.createTaskExecution(request);
        return ResponseEntity.ok(new TaskExecutionResponseDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskExecutionResponseDTO> updateTaskExecution(@PathVariable Long id, @RequestBody TaskExecutionRequestDTO request) {
        TaskExecution updated = taskExecutionService.updateTaskExecution(id, request);
        return ResponseEntity.ok(new TaskExecutionResponseDTO(updated));
    }

}

package com.pasdm.integration.controller;

import com.pasdm.integration.dto.TaskExecutionDTO;
import com.pasdm.integration.dto.TaskExecutionRequestDTO;
import com.pasdm.integration.dto.TaskExecutionResponseDTO;
import com.pasdm.integration.model.TaskExecution;
import com.pasdm.integration.repository.TaskExecutionRepository;
import com.pasdm.integration.service.TaskExecutionService;
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


@RestController
@RequestMapping("/api/etl/task_execution")
public class TaskExecutionController {

    @Autowired
    private TaskExecutionRepository taskExecutionRepository;

    @Autowired
    private TaskExecutionService taskExecutionService;

    @GetMapping
        public List<TaskExecutionDTO> getTaskExecution() {
        return taskExecutionService.getTaskExecution();
    }

    @GetMapping("/{id}")
    public TaskExecution findById(@PathVariable Long id) {
        return taskExecutionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TaskExecution not found with id " + id));
    }

    /*
    @GetMapping("/by-filter")
    public ResponseEntity<Page<TaskExecutionDTO>> getTaskExecutionByFinishAt(
            @RequestParam OffsetDateTime finishedAt,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "finishedAt") String sortField,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortField);

        Pageable pageable = PageRequest.of(page, size, sort);

        return ResponseEntity.ok(taskExecutionService.getTaskExecutionByFinishAt(finishedAt, pageable));
    }
     */

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

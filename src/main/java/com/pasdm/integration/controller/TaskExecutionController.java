package com.pasdm.integration.controller;

import com.pasdm.integration.dto.TaskExecutionRequestDTO;
import com.pasdm.integration.dto.TaskExecutionResponseDTO;
import com.pasdm.integration.model.TaskExecution;
import com.pasdm.integration.repository.TaskExecutionRepository;
import com.pasdm.integration.service.TaskExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etl/task_execution")
public class TaskExecutionController {

    @Autowired
    private TaskExecutionRepository taskExecutionRepository;

    @Autowired
    private TaskExecutionService taskExecutionService;

    @GetMapping
    public List<TaskExecution> getTaskExecution() {
        return taskExecutionRepository.findAll();
    }

    @GetMapping("/{id}")
    public TaskExecution findById(@PathVariable Long id) {
        return taskExecutionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("TaskExecution not found with id " + id));
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

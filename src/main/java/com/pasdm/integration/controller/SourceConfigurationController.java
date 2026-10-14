package com.pasdm.integration.controller;

import com.pasdm.integration.dto.SourceConfigurationRequestDTO;
import com.pasdm.integration.dto.SourceConfigurationResponseDTO;
import com.pasdm.integration.dto.TaskExecutionRequestDTO;
import com.pasdm.integration.model.SourceConfiguration;
import com.pasdm.integration.repository.SourceConfigurationRepository;
import com.pasdm.integration.service.SourceConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etl/source_configuration")
public class SourceConfigurationController {

    @Autowired
    private SourceConfigurationRepository  sourceConfigurationRepository;

    @Autowired
    private SourceConfigurationService sourceConfigurationService;

    @GetMapping
    public List<SourceConfiguration> getSourceConfigurations() {
        return sourceConfigurationRepository.findAll();
    }

    @GetMapping("/{id}")
    public SourceConfiguration findById(@PathVariable Long id) {
        return sourceConfigurationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("SourceConfiguration not found with id " + id));
    }

    @PostMapping
    public ResponseEntity<SourceConfigurationResponseDTO> createSourceConfiguration(@RequestBody SourceConfigurationRequestDTO request) {
        SourceConfiguration saved = sourceConfigurationService.createSourceConfiguration(request);
        return ResponseEntity.ok(new SourceConfigurationResponseDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SourceConfigurationResponseDTO> updateSourceConfiguration(@PathVariable Long id, @RequestBody SourceConfigurationRequestDTO request) {
        SourceConfiguration udpated = sourceConfigurationService.updateSourceConfiguration(id, request);
        return ResponseEntity.ok(new SourceConfigurationResponseDTO(udpated));
    }

}

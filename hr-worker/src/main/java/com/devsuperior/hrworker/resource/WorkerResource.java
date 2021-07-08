package com.devsuperior.hrworker.resource;

import com.devsuperior.hrworker.entities.Worker;
import com.devsuperior.hrworker.repositories.WorkerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RefreshScope
@RestController
@RequestMapping("/workers")
public class WorkerResource {

    private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);

    private final Environment env;

    private final WorkerRepository workerRepository;

    @Value("${test.config}")
    private String testConfig;

    public WorkerResource(Environment env, WorkerRepository workerRepository) {
        this.env = env;
        this.workerRepository = workerRepository;
    }

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        List<Worker> list = workerRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> findById(@PathVariable("id") Long id) {
        Optional<Worker> worker = workerRepository.findById(id);
        return ResponseEntity.ok(worker.orElse(null));
    }

    @GetMapping("/configs")
    public ResponseEntity<String> getConfigs() {
        logger.info("Teste config"+ testConfig);
        return ResponseEntity.ok(testConfig);
    }

}

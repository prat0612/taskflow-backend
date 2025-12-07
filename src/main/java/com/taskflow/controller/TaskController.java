package com.taskflow.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.taskflow.dto.TaskRequest;
import com.taskflow.dto.TaskResponse;
import com.taskflow.service.TaskService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Slf4j
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTasks(Principal principal) {
        String email = principal.getName();
        log.debug("Fetching tasks for user {}", email);
        return ResponseEntity.ok(taskService.getTasksForUser(email));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            Principal principal,
            @RequestBody TaskRequest request
    ) {
        String email = principal.getName();
        log.info("Creating task for user {}", email);
        return ResponseEntity.ok(taskService.createTask(email, request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            Principal principal,
            @PathVariable Long id,
            @RequestBody TaskRequest request
    ) {
        String email = principal.getName();
        log.info("Updating task {} for user {}", id, email);
        return ResponseEntity.ok(taskService.updateTask(email, id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(
            Principal principal,
            @PathVariable Long id
    ) {
        String email = principal.getName();
        log.info("Deleting task {} for user {}", id, email);
        taskService.deleteTask(email, id);
        return ResponseEntity.noContent().build();
    }
}

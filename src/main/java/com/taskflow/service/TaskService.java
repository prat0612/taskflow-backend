package com.taskflow.service;

import java.util.List;

import com.taskflow.dto.TaskRequest;
import com.taskflow.dto.TaskResponse;

public interface TaskService {

    TaskResponse createTask(String userEmail, TaskRequest request);

    List<TaskResponse> getTasksForUser(String userEmail);

    TaskResponse updateTask(String userEmail, Long taskId, TaskRequest request);

    void deleteTask(String userEmail, Long taskId);
}

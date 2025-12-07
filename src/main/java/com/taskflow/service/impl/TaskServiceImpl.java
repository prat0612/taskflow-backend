package com.taskflow.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taskflow.dto.TaskRequest;
import com.taskflow.dto.TaskResponse;
import com.taskflow.entity.Task;
import com.taskflow.entity.User;
import com.taskflow.exception.TaskNotFoundException;
import com.taskflow.exception.UnauthorizedException;
import com.taskflow.exception.UserNotFoundException;
import com.taskflow.repository.TaskRepository;
import com.taskflow.repository.UserRepository;
import com.taskflow.service.TaskService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    public List<TaskResponse> getTasksForUser(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return user.getTasks().stream()
                .map(TaskResponse::from)
                .toList();
    }

    @Override
    public TaskResponse createTask(String email, TaskRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .user(user)
                .build();

        taskRepository.save(task);

        return TaskResponse.from(task);
    }

    @Override
    public TaskResponse updateTask(String email, Long id, TaskRequest request) {
        
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        if (!task.getUser().getEmail().equals(email)) {
            throw new UnauthorizedException("Not your task");
        }

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());

        taskRepository.save(task);

        return TaskResponse.from(task);
    }

    @Override
    public void deleteTask(String email, Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        if (!task.getUser().getEmail().equals(email)) {
            throw new UnauthorizedException("Not your task");
        }

        taskRepository.delete(task);
    }
}

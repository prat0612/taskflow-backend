package com.taskflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskflow.entity.Task;
import com.taskflow.entity.Status;
import com.taskflow.entity.User;

import java.time.LocalDateTime;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(User user);

    List<Task> findByDeadlineBeforeAndStatusNot(LocalDateTime now, Status status);
}

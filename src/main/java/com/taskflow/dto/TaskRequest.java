package com.taskflow.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.taskflow.entity.Status;

import lombok.Data;

@Data
public class TaskRequest {

    private String title;
    private String description;
    private Status status;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime deadline;
}

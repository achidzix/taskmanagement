package com.taskmanagement.taskmanagement.tasks.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateTaskDto(
        @NotEmpty String title,
        @NotEmpty  String description,
        LocalDateTime deadline
) {
}

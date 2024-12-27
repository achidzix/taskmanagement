package com.taskmanagement.taskmanagement.tasks.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EditTaskDto(
        @NotNull @NotBlank String title,
        @NotNull @NotBlank String description,
        LocalDateTime deadline
) {
}

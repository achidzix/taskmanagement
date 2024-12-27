package com.taskmanagement.taskmanagement.authentication.Dto;

import jakarta.validation.constraints.NotEmpty;

public record AuthenticationRequestDto (
     @NotEmpty String username,
     @NotEmpty String password
){}

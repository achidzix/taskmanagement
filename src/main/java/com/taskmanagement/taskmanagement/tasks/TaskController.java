package com.taskmanagement.taskmanagement.tasks;

import com.taskmanagement.taskmanagement.tasks.dto.CreateTaskDto;
import com.taskmanagement.taskmanagement.tasks.dto.EditTaskDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public ResponseEntity<List<Task>> getTasks(){
        return this.taskService.getAllTasks();
    }

    @PostMapping()
    public ResponseEntity<Task> createTask(@Valid @RequestBody CreateTaskDto task){
        return this.taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> editTask(@PathVariable UUID id,@Valid @RequestBody EditTaskDto task){
        return this.taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id){
        return this.taskService.deleteTask(id);
    }
}

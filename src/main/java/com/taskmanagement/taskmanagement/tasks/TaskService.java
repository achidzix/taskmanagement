package com.taskmanagement.taskmanagement.tasks;

import com.taskmanagement.taskmanagement.tasks.dto.CreateTaskDto;
import com.taskmanagement.taskmanagement.tasks.dto.EditTaskDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class TaskService {

    final private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> task = taskRepository.findAll();

        return new ResponseEntity<>(task,HttpStatus.OK);
    }

    public ResponseEntity<Task>  createTask(CreateTaskDto task){
        Task task1 = new Task();
        task1.setTitle(task.title());
        task1.setDescription(task.description());
        task1.setDeadline(task.deadline());
        task1.setLast_updated(LocalDateTime.now());
        Task data = taskRepository.save(task1);
        return new ResponseEntity<>(data,HttpStatus.CREATED);
    }
    public ResponseEntity<Task> updateTask(UUID id, EditTaskDto data){
        Optional<Task> task = taskRepository.findById(id);
        if(task.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Task not found");
        }
        Task selectedTask = task.get();
        selectedTask.setTitle(data.title());
        selectedTask.setDescription(data.description());
        selectedTask.setDeadline(data.deadline());
        selectedTask.setLast_updated(LocalDateTime.now());
        taskRepository.save(selectedTask);
        return new ResponseEntity<>(selectedTask,HttpStatus.OK);
    }

    public  ResponseEntity<Void> deleteTask(UUID id){
        Optional<Task> task = taskRepository.findById(id);
        if(task.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Task not found");
        }
        taskRepository.delete(task.get());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}

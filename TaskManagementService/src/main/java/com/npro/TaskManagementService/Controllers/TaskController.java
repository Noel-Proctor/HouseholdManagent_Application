package com.npro.TaskManagementService.Controllers;
import com.npro.TaskManagementService.Payload.TaskDTO;
import com.npro.TaskManagementService.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/tasks")
@RestController
public class TaskController {


    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid TaskDTO newTask){
        TaskDTO taskDto = taskService.createTask(newTask);
        return new ResponseEntity<>(taskDto, HttpStatus.CREATED);
    }

//    Update Task Name/ description/ dueDate

//    Delete Task

//    Get all tasks with pagination and filters

//    get a single task by Id.

//    Update Status of a specific task.
}

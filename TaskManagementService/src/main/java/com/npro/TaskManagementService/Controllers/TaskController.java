package com.npro.TaskManagementService.Controllers;
import com.npro.TaskManagementService.Payload.TaskDTO;
import com.npro.TaskManagementService.Service.TaskService;
import jakarta.validation.Valid;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
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


}

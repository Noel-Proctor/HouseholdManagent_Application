package com.npro.TaskManagementService.Controllers;
import com.npro.TaskManagementService.Payload.TaskDTO;
import com.npro.TaskManagementService.Payload.TaskResponse;
import com.npro.TaskManagementService.Payload.TaskResponsePOJO;
import com.npro.TaskManagementService.Service.TaskService;
import com.npro.TaskManagementService.config.AppConstants;
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
    public ResponseEntity<TaskResponsePOJO> createTask(@RequestBody @Valid TaskDTO newTask){
        TaskResponsePOJO response = taskService.createTask(newTask);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    Update Task
    @PostMapping("/update")
    public ResponseEntity<TaskResponsePOJO> updateTask(@RequestBody @Valid TaskDTO taskToUpdateDTO){
        TaskResponsePOJO response = taskService.updateTask(taskToUpdateDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    Delete Task
    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId){
        TaskResponsePOJO response = taskService.deleteTask(taskId);
        return new ResponseEntity<>(response.getMessage(), HttpStatus.OK);
    }

//    get a single task by Id.
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponsePOJO> getTaskById(@PathVariable Long taskId){
        TaskResponsePOJO response = taskService.getTask(taskId);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }
//   Get all tasks with pagination and filters


    @GetMapping("/viewAll")
    public ResponseEntity<TaskResponse> getAllTasks(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "SortBy", defaultValue =  AppConstants.TASK_DEFAULT_SORT_BY ) String sortBy,
            @RequestParam(name = "direction", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION) String direction,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer pageSize){
        System.out.println("Debugger");
        TaskResponse response = taskService.getTasks(pageNumber, sortBy, direction, pageSize);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



//    Update Status of a specific task.
}

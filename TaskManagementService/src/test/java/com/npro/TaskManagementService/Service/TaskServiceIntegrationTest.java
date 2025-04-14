package com.npro.TaskManagementService.Service;

import com.npro.TaskManagementService.Exceptions.APIException;
import com.npro.TaskManagementService.Exceptions.NotFound;
import com.npro.TaskManagementService.Model.Task;
import com.npro.TaskManagementService.Model.TaskStatus;
import com.npro.TaskManagementService.Payload.TaskDTO;
import com.npro.TaskManagementService.Payload.TaskResponsePOJO;
import com.npro.TaskManagementService.Repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@Transactional
public class TaskServiceIntegrationTest {

    private final TaskService taskService;
    private final TaskRepository taskRepository;
    private Task newTask;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public TaskServiceIntegrationTest(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }


    @BeforeEach
    void setUp() {
        newTask = new Task();
        newTask.setTaskName("test_task");
        newTask.setDescription("Test Description");
        newTask.setDueDate(LocalDate.now().plusDays(1));

    }

    @Test
    @Order(1)
    void CreateTaskIntegrationTest() {

        TaskResponsePOJO response = taskService.createTask(modelMapper.map(newTask, TaskDTO.class));
        TaskDTO savedTask = response.getTask();
        //Validate the result
        assertNotNull(savedTask.getGuid(), "GUID should not be null");
        assertEquals("Task name should match", newTask.getTaskName(), savedTask.getTaskName());
        assertEquals("Description should match", newTask.getDescription(), savedTask.getDescription());
        assertEquals("Due date should match", newTask.getDueDate(), savedTask.getDueDate());
        assertEquals("Created date should not be null", LocalDate.now(), savedTask.getCreatedDate());
        assertEquals("Status should be TO_DO", TaskStatus.TO_DO, savedTask.getStatus());
//        Verify I can retrieve new record from DB
        Task result = taskRepository.findByGuid(savedTask.getGuid()).orElseThrow(()->
                new NotFound("Task not found. Please contact your administrator"));

        assertNotNull(result, "Task should not be null");
        assertEquals("Guids should match", result.getGuid(), savedTask.getGuid());
    }


    @Test
    @Order(2)
    void testTaskUpdate(){

        TaskResponsePOJO response = taskService.createTask(modelMapper.map(newTask, TaskDTO.class));
        TaskDTO task = response.getTask();
        assertNotNull(task);

        TaskDTO  taskToUpdateDTO = modelMapper.map(task, TaskDTO.class);
        taskToUpdateDTO.setTaskName("Updated Task Name");
        taskToUpdateDTO.setDescription("Updated Description");
        taskToUpdateDTO.setDueDate(LocalDate.now().plusDays(10));

        TaskResponsePOJO updateResponse= taskService.updateTask(taskToUpdateDTO);

        TaskDTO updatedTaskDTO = updateResponse.getTask();

        assertNotNull(updatedTaskDTO);
        assertEquals("Task Name Should Match", taskToUpdateDTO.getTaskName(), updatedTaskDTO.getTaskName());
        assertEquals("Description Should Match", taskToUpdateDTO.getDescription(), updatedTaskDTO.getDescription());
        assertEquals("Status Should Match", taskToUpdateDTO.getStatus(), updatedTaskDTO.getStatus());
        assertEquals("Completed Date Should Match", taskToUpdateDTO.getCompletedDate(), updatedTaskDTO.getCompletedDate());
        assertEquals("Created Date Should Match", taskToUpdateDTO.getCreatedDate(), updatedTaskDTO.getCreatedDate());
        assertEquals("Guid Should Match", taskToUpdateDTO.getGuid(), updatedTaskDTO.getGuid());
        Task updatedTask = taskRepository.findByGuid(updatedTaskDTO.getGuid()).orElseThrow(()->
                new NotFound("Task not found. Please contact your administrator")
                );
        assertEquals("Task Id Should Match", response.getTaskId(), updatedTask.getTaskId());
    }

    @Test
    void testTaskDelete(){

        TaskResponsePOJO response = taskService.createTask(modelMapper.map(newTask, TaskDTO.class));
        Task task = taskRepository.findById(response.getTaskId()).orElseThrow(()->
                new NotFound("Task not found. Please contact your administrator"));

        assertNotNull(task);

        taskService.deleteTask(task.getTaskId());
        Optional<Task> deletedTask = taskRepository.findById(task.getTaskId());
        assertFalse(deletedTask.isPresent());

//        test exception thrown if invalid id is passed
        assertThrows(APIException.class, ()->{
            taskService.deleteTask(null);
        });

        assertThrows(APIException.class, ()->{
            taskService.deleteTask(-1L);
        });
    }


    @Test
    void testGetTask(){

        TaskResponsePOJO taskToFetch = taskService.createTask(modelMapper.map(newTask, TaskDTO.class));
        TaskResponsePOJO response = taskService.getTask(taskToFetch.getTaskId());
        TaskDTO fetchedTask = response.getTask();

        assertNotNull(fetchedTask);
        assertEquals("Task Name Should Match", taskToFetch.getTask().getTaskName(), fetchedTask.getTaskName());
        assertEquals("Description Should Match", taskToFetch.getTask().getDescription(), fetchedTask.getDescription());
        assertEquals("Status Should Match", taskToFetch.getTask().getStatus(), fetchedTask.getStatus());
        assertEquals("Completed Date Should Match", taskToFetch.getTask().getCompletedDate(), fetchedTask.getCompletedDate());
        assertEquals("Created Date Should Match", taskToFetch.getTask().getCreatedDate(), fetchedTask.getCreatedDate());
        assertEquals("Guid Should Match", taskToFetch.getTask().getGuid(), fetchedTask.getGuid());
        assertEquals("Id should match", taskToFetch.getTaskId(), response.getTaskId());

    }

}

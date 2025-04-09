package com.npro.TaskManagementService.Service;

import com.npro.TaskManagementService.Model.Task;
import com.npro.TaskManagementService.Payload.TaskDTO;
import com.npro.TaskManagementService.Repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
@Transactional
public class TaskServiceIntegrationTest {

    private final TaskService taskService;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceIntegrationTest(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @Test
    void CreateTaskIntegrationTest() {

        TaskDTO task = new TaskDTO();
        task.setTaskName("test_task");
        task.setDescription("Test Description");
        task.setDueDate(LocalDate.now().plusDays(1));
        TaskDTO savedTask = taskService.createTask(task);

        //Validate the result
        assertNotNull(savedTask.getGuid(), "GUID should not be null");
        assertEquals("Task name should match", task.getTaskName(), savedTask.getTaskName());
        assertEquals("Description should match", task.getDescription(), savedTask.getDescription());
        assertEquals("Due date should match", task.getDueDate(), savedTask.getDueDate());
        assertEquals("Created date should not be null", LocalDate.now(), savedTask.getCreatedDate());
//        Verify I can retrieve new record from DB
        Task result = taskRepository.findByGuid(savedTask.getGuid());
        assertNotNull(result, "Task should not be null");
        assertEquals("Guids should match", result.getGuid(), savedTask.getGuid());
    }

}

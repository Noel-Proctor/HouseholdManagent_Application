package com.npro.TaskManagementService.Service;

import com.npro.TaskManagementService.Model.Task;
import com.npro.TaskManagementService.Model.TaskStatus;
import com.npro.TaskManagementService.Payload.TaskDTO;
import com.npro.TaskManagementService.Repositories.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TaskServiceTest {


    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TaskServiceImpl taskService;


    private Task task;
    private Task savedTask;
    private TaskDTO taskDTO;
    private TaskDTO savedTaskDTO;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        task = new Task();
        task.setTaskName("test_task");
        task.setDescription("Test Description");
        task.setDueDate(LocalDate.now().plusDays(1));

        taskDTO = new TaskDTO();
        taskDTO.setTaskName("test_task");
        taskDTO.setDescription("Test Description");
        taskDTO.setDueDate(LocalDate.now().plusDays(1));

        savedTask = new Task();
        savedTask.setTaskName("test_task");
        savedTask.setDescription("Test Description");
        savedTask.setDueDate(LocalDate.now().plusDays(1));
        savedTask.setGuid(UUID.randomUUID().toString());
        savedTask.setStatus(TaskStatus.TO_DO);
        savedTask.setCreatedDate(LocalDate.now());

        savedTaskDTO = new TaskDTO();
        savedTaskDTO.setTaskName("test_task");
        savedTaskDTO.setDescription("Test Description");
        savedTaskDTO.setDueDate(LocalDate.now().plusDays(1));
        savedTaskDTO.setGuid(UUID.randomUUID().toString());
        savedTaskDTO.setStatus(TaskStatus.TO_DO);
        savedTaskDTO.setCreatedDate(LocalDate.now());

        when(modelMapper.map(taskDTO,Task.class)).thenReturn(task);
        when(modelMapper.map(task,TaskDTO.class)).thenReturn(taskDTO);
        when(modelMapper.map(savedTask,TaskDTO.class)).thenReturn(savedTaskDTO);
        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);
    }


    @Test
    void testValidCreateTask(){
        TaskDTO result = taskService.createTask(taskDTO);
        assert(result.getGuid().equals(savedTaskDTO.getGuid()));
        assert(result.getStatus().equals(savedTaskDTO.getStatus()));
        assert(result.getCreatedDate().equals(savedTaskDTO.getCreatedDate()));
        assert(result.getTaskName().equals(savedTaskDTO.getTaskName()));
        assert(result.getDescription().equals(savedTaskDTO.getDescription()));
        assert(result.getDueDate().equals(savedTaskDTO.getDueDate()));
    }


    @Test
    void testInvalidTaskCreate(){

    }




}

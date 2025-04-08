package com.npro.TaskManagementService.Service;

import com.npro.TaskManagementService.Model.Task;
import com.npro.TaskManagementService.Model.TaskStatus;
import com.npro.TaskManagementService.Payload.TaskDTO;
import com.npro.TaskManagementService.Repositories.TaskRepository;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper){
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDto) {
            Task task = modelMapper.map(taskDto, Task.class);
            task.setGuid(UUID.randomUUID().toString());
            task.setStatus(TaskStatus.TO_DO);
            task.setCreatedDate(LocalDate.now());
            TaskDTO newTask = modelMapper.map(taskRepository.save(task), TaskDTO.class);
            return newTask;
    }

}

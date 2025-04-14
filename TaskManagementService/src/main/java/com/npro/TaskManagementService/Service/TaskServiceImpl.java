package com.npro.TaskManagementService.Service;

import com.npro.TaskManagementService.Exceptions.APIException;
import com.npro.TaskManagementService.Exceptions.NotFound;
import com.npro.TaskManagementService.Model.Task;
import com.npro.TaskManagementService.Model.TaskStatus;
import com.npro.TaskManagementService.Payload.TaskDTO;
import com.npro.TaskManagementService.Payload.TaskResponse;
import com.npro.TaskManagementService.Payload.TaskResponsePOJO;
import com.npro.TaskManagementService.Repositories.TaskRepository;
import com.npro.TaskManagementService.TaskManagementServiceApplication;
import org.modelmapper.ModelMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper, TaskManagementServiceApplication taskManagementServiceApplication) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TaskResponsePOJO createTask(TaskDTO taskDto) {

        taskDto.setGuid(UUID.randomUUID());
        taskDto.setStatus(TaskStatus.TO_DO);
        taskDto.setCreatedDate(LocalDate.now());
        Task task = modelMapper.map(taskDto, Task.class);

        Task saved_task = taskRepository.save(task);
        TaskDTO saved_taskDTO = modelMapper.map(saved_task, TaskDTO.class);

        TaskResponsePOJO taskResponse = new TaskResponsePOJO("Task Created Successfully", saved_task.getTaskId(), saved_taskDTO);
        return taskResponse;
    }

    @Override
    public TaskResponsePOJO updateTask(TaskDTO taskToUpdateDTO) {

        Task task = taskRepository.findByGuid(taskToUpdateDTO.getGuid()).orElseThrow(() ->
                new NotFound("Task not found. Please contact your administrator"));
        task.setTaskName(taskToUpdateDTO.getTaskName());
        task.setDescription(taskToUpdateDTO.getDescription());
        task.setDueDate(taskToUpdateDTO.getDueDate());
        task.setUpdatedDate(LocalDate.now());
        Task updatedTask = taskRepository.save(task);
        TaskResponsePOJO response = new TaskResponsePOJO("Task Updated Successfully", updatedTask.getTaskId(), modelMapper.map(updatedTask, TaskDTO.class));
        return response;

    }

    @Override
    public TaskResponsePOJO deleteTask(Long taskId) {
        if (taskId == null || taskId <= 0) {
            throw new APIException("You must provide a valid Task Id");
        }


        try {
            taskRepository.deleteById(taskId);
            return new TaskResponsePOJO("Task Deleted Successfully", taskId, null);
        }

        catch (IllegalArgumentException e) {
            throw new NotFound("Task not found. Please contact your administrator");
        }
    }


    @Override
    public TaskResponsePOJO getTask(Long taskId) {

        if(taskId != null && taskId > 0){
            Task task = taskRepository.findById(taskId).orElseThrow(()-> new NotFound("Task not found."));
            return new TaskResponsePOJO("Task Found", taskId, modelMapper.map(task, TaskDTO.class));

        }else{
            throw new APIException("You must provide a valid Task Id");
        }
    }

    @Override
    public TaskResponse getTasks(Integer pageNumber, String sortBy, String direction, Integer pageSize) {

        Sort sort = direction.equalsIgnoreCase("DESC") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<TaskResponsePOJO> taskPage = taskRepository.findAll(pageable).map(
                task -> {
                    return new TaskResponsePOJO("Success", task, modelMapper);
                }
        );
        List<TaskResponsePOJO> response = taskPage.getContent();
        return TaskResponse.buildTaskResponse(response, taskPage);
    }
}

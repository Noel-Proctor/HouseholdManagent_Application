package com.npro.TaskManagementService.Service;

import com.npro.TaskManagementService.Payload.TaskDTO;
import com.npro.TaskManagementService.Payload.TaskResponse;
import com.npro.TaskManagementService.Payload.TaskResponsePOJO;

public interface TaskService {
    TaskResponsePOJO createTask(TaskDTO task);

    TaskResponsePOJO updateTask(TaskDTO taskToUpdateDTO);

    TaskResponsePOJO deleteTask(Long taskId);

    TaskResponsePOJO getTask(Long taskId);

    TaskResponse getTasks(Integer pageNumber, String sortBy, String direction, Integer pageSize);
}

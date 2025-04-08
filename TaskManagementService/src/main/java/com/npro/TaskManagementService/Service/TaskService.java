package com.npro.TaskManagementService.Service;

import com.npro.TaskManagementService.Payload.TaskDTO;

public interface TaskService {
    TaskDTO createTask(TaskDTO task);
}

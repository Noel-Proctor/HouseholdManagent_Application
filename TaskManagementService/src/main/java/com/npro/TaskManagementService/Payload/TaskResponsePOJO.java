package com.npro.TaskManagementService.Payload;

import com.npro.TaskManagementService.Model.Task;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
public class TaskResponsePOJO {

    private String message;
    private Long taskId;
    private TaskDTO task;

    public TaskResponsePOJO(String message, Long taskId, TaskDTO taskDTO) {
        this.message = message;
        this.taskId = taskId;
        this.task = taskDTO;
    }

    public TaskResponsePOJO(String message, Task task_In, ModelMapper modelMapper) {
        this.message = message;
        this.taskId = task_In.getTaskId();
        this.task = modelMapper.map(task_In, TaskDTO.class);

    }
}

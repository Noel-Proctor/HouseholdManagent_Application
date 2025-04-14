package com.npro.TaskManagementService.Payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    private List<TaskResponsePOJO> tasks;
    private boolean firstPage;
    private Integer totalPages;
    private Integer currentPage;
    private Integer totalElements;
    private Integer size;
    private Integer total;
    private boolean lastPage;

    public TaskResponse(List<TaskResponsePOJO> taskList, Page<TaskResponsePOJO> page) {
        tasks = taskList;
        firstPage = page.isFirst();
        lastPage = page.isLast();

    }

    public static TaskResponse buildTaskResponse(List<TaskResponsePOJO> taskList, Page<TaskResponsePOJO> page) {
        return new TaskResponse(taskList, page);


    }

}

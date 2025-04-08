package com.npro.TaskManagementService.Payload;
import com.npro.TaskManagementService.Model.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private String taskName;
    private String description;

    private TaskStatus status;
    private String GUID;
    private LocalDate dueDate;
    private LocalDate completedDate;
}

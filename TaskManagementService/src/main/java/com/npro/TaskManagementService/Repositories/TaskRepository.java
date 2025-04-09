package com.npro.TaskManagementService.Repositories;

import com.npro.TaskManagementService.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByGuid(String guid);
}

package com.npro.TaskManagementService.Repositories;

import com.npro.TaskManagementService.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByGuid(UUID guid);
}

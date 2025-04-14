package com.npro.TaskManagementService.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.time.LocalDate;
import java.util.UUID;


@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TaskId;

    @NotNull
    private UUID guid;

    @NotBlank(message = "Task name cannot be empty.")
    @Size(max= 100, message="Task name cannot be greater than 100 characters.")
    private String taskName;

    @Size(max= 250, message= "Task description cannot be greater than 250 characters.")
    private String description;

    private TaskStatus status;

    @FutureOrPresent
    private LocalDate dueDate;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private LocalDate completedDate;


    public void setCompletedDate(LocalDate completedDate) {

        if(this.createdDate != null && completedDate != null && completedDate.isBefore(this.createdDate)) {
            throw new IllegalArgumentException("Completed date cannot be before created date");
        }
        this.completedDate = completedDate;
    }
}

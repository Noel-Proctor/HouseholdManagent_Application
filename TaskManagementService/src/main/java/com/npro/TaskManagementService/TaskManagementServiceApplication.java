package com.npro.TaskManagementService;

import com.npro.TaskManagementService.Model.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagementServiceApplication.class, args);

		Task task = new Task();
		task.setGuid("test-guid"); // <-- This should not give an error
		System.out.println(task.getGuid());
	}

}

package com.npro.TaskManagementService.config;


import com.npro.TaskManagementService.Payload.TaskDTO;
import com.npro.TaskManagementService.Repositories.TaskRepository;
import com.npro.TaskManagementService.Service.TaskService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }



    @Bean
    @Transactional
    public CommandLineRunner initData(TaskService taskService){

        return args ->{

            if(AppConstants.CLEAN_DB){
                System.out.println("Cleaning DB - Still need to implement when required");

            }

            if(AppConstants.INIT_DATA){
                System.out.println("Initializing DB");

                TaskDTO task1 = new TaskDTO();
                task1.setTaskName("Walk the dog");
                task1.setDescription("I must walk the dog today or he will get fat");
                task1.setDueDate(LocalDate.now());

                TaskDTO task2 = new TaskDTO();
                task2.setTaskName("Dinner at 9pm with my family");
                task2.setDescription("We have booked a nice restaurant for my family");
                task2.setDueDate(LocalDate.now().plusWeeks(1));

                TaskDTO task3 = new TaskDTO();
                task3.setTaskName("Wash the dishes");
                task3.setDescription("I need to clean them really well");
                task3.setDueDate(LocalDate.now());

                TaskDTO task4 = new TaskDTO();
                task4.setTaskName("Fix the fence");
                task4.setDescription("I need the fix the fence before my family come over with their dog.");
                task4.setDueDate(LocalDate.now().plusMonths(1));

                taskService.createTask(task1);
                taskService.createTask(task2);
                taskService.createTask(task3);
                taskService.createTask(task4);
            }
        };
    }
}

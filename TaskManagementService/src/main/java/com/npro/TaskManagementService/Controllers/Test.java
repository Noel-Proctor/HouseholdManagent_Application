package com.npro.TaskManagementService.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/tasks")
@RestController
public class Test {

    @GetMapping("/test")
    public ResponseEntity<String> testApi(){
        return ResponseEntity.ok("Task Service Hello World");
    }
}

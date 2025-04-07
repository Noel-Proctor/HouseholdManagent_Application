package com.npro.BudgetManagementService.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/budget")
@RestController
public class Test {

    @GetMapping("/test")
    public ResponseEntity<String> testApi(){

        return ResponseEntity.ok("Budget Service Hello World");
    }
}

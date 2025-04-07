package com.npro.UserManagementService.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/um")
@RestController
public class Test {

    @GetMapping("/test")
    public ResponseEntity<String> testApi(){
        return ResponseEntity.ok("User Management Service Hello World");
    }
}

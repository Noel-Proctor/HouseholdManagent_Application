package com.npro.BudgetManagementService.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    private String description;
    private String source;
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;


}

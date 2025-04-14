package com.npro.BudgetManagementService.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToMany(mappedBy = "budget")
    private List<Income> incomes;

}

package com.exam.GestionEmployee.DTO;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String position;
}
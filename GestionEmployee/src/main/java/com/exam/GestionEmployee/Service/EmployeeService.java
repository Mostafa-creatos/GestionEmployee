package com.exam.GestionEmployee.Service;

import com.exam.GestionEmployee.DTO.EmployeeDTO;
import com.exam.GestionEmployee.Model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(EmployeeDTO employeeDTO);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Long id, EmployeeDTO employeeDTO);
    void deleteEmployee(Long id);
}

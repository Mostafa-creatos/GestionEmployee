package com.exam.GestionEmployee.Service.ServiceImpl;

import com.exam.GestionEmployee.DTO.EmployeeDTO;
import com.exam.GestionEmployee.Model.Employee;
import com.exam.GestionEmployee.Repositories.EmployeeRepository;
import com.exam.GestionEmployee.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            throw new RuntimeException(messageSource.getMessage("email.already.in.use", null, Locale.getDefault()));
        }
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPosition(employeeDTO.getPosition());
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                new RuntimeException(messageSource.getMessage("employee.not.found", null, Locale.getDefault())));
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new RuntimeException(messageSource.getMessage("employee.not.found", null, Locale.getDefault())));
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPosition(employeeDTO.getPosition());
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException(messageSource.getMessage("employee.not.found", null, Locale.getDefault()));
        }
        employeeRepository.deleteById(id);
    }
}

package com.example.employeeservice.service;

import com.example.employeeservice.model.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        log.info("Fetching all employees");
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        log.info("Fetching employee with id: {}", id);
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        log.info("Saving new employee: {}", employee);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        log.info("Deleting employee with id: {}", id);
        employeeRepository.deleteById(id);
    }
}
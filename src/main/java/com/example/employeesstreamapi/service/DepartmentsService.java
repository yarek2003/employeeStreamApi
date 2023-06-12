package com.example.employeesstreamapi.service;

import com.example.employeesstreamapi.exceptions.EmployeeNotFoundException;
import com.example.employeesstreamapi.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class DepartmentsService {

    private final EmployeeService employeeService;

    public DepartmentsService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    public Employee maxSalary(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .max(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("Not found"));
    }
    public Employee minSalary(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .min(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException("Not found"));
    }
    public Collection<Employee> allByDepartment(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> all() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartmentId()));
    }
}

package com.example.employeesstreamapi.controller;


import com.example.employeesstreamapi.model.Employee;
import com.example.employeesstreamapi.service.DepartmentsService;
import com.example.employeesstreamapi.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {
    private final DepartmentsService service;

    public DepartmentsController(DepartmentsService service) {
        this.service = service;
    }
    @GetMapping("/max-salary")
    public Employee maxSalary(@RequestParam int departmentId) {
        return service.maxSalary(departmentId);
    }
    @GetMapping("/min-salary")
    public Employee minSalary(@RequestParam int departmentId) {
        return service.minSalary(departmentId);
    }
    @GetMapping("/all-by-department")
    public Collection<Employee> allByDepartment(@RequestParam int departmentId) {
        return service.allByDepartment(departmentId);
    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> all() {
        return service.all();

    }

}

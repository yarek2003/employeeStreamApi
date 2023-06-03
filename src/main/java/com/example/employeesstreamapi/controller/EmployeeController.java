package com.example.employeesstreamapi.controller;

import com.example.employeesstreamapi.model.Employee;
import com.example.employeesstreamapi.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam String firstName, @RequestParam String lastName,
                        @RequestParam int departmentId, @RequestParam int salary) {
        return service.add(firstName, lastName, departmentId, salary);
    }
    @GetMapping("/remove")
    public Employee remove(@RequestParam String firstName, @RequestParam String lastName){
        return service.del(firstName,lastName);
    }
    @GetMapping("/find")
    public Employee find(@RequestParam String firstName, @RequestParam String lastName){
        return service.find(firstName,lastName);
    }
    @GetMapping("/all")
    public String all() {
        return service.getAll().toString();
    }

}
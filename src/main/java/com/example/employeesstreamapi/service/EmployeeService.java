package com.example.employeesstreamapi.service;

import com.example.employeesstreamapi.model.Employee;
import com.example.employeesstreamapi.exceptions.EmployeeAlreadyAddedException;
import com.example.employeesstreamapi.exceptions.EmployeeNotFoundException;
import com.example.employeesstreamapi.exceptions.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {
    public static final int MAX_SIZE = 100;

    private final Map<String, Employee> employees;

    public EmployeeService() {
        this.employees = new HashMap<>();
    }

    public Employee add (String firstName, String lastName, int departmentId, int salary){
        if (employees.size()>MAX_SIZE){
            throw new EmployeeStorageIsFullException();
        }
        String key = firstName + " " + lastName;
        if (employees.containsKey(key)){
            throw new EmployeeAlreadyAddedException("Employee already added");
        }
        Employee employee = new Employee(firstName, lastName, departmentId, salary);
        employees.put(key, employee);
        return employee;
    }
    public Employee del (String firstName, String lastName){
        String key = firstName + " " + lastName;
        if (!employees.containsKey(key)){
            throw new EmployeeNotFoundException("Employee not found");
        }
        Employee removed = employees.remove(key);
        return removed;
    }

    public Employee find(String firstName, String lastName) {
        var key = firstName + " " + lastName;
        var found = employees.get(key);
        if (found == null) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        return found;
    }


    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
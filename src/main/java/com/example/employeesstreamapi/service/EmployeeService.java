package com.example.employeesstreamapi.service;

import com.example.employeesstreamapi.exceptions.InvalidInputException;
import com.example.employeesstreamapi.model.Employee;
import com.example.employeesstreamapi.exceptions.EmployeeAlreadyAddedException;
import com.example.employeesstreamapi.exceptions.EmployeeNotFoundException;
import com.example.employeesstreamapi.exceptions.EmployeeStorageIsFullException;
import org.apache.commons.lang3.StringUtils;
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
        checkInputDetails(firstName, lastName);
        if (employees.size()>MAX_SIZE){
            throw new EmployeeStorageIsFullException();
        }
        String key = firstName + " " + lastName;
        if (employees.containsKey(key)){
            throw new EmployeeAlreadyAddedException("Employee already added");
        }
        Employee employee = new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), departmentId, salary);
        employees.put(key, employee);
        return employee;
    }
    public Employee del (String firstName, String lastName){
        checkInputDetails(firstName, lastName);
        String key = firstName + " " + lastName;
        if (!employees.containsKey(key)){
            throw new EmployeeNotFoundException("Employee not found");
        }
        Employee removed = employees.remove(key);
        return removed;
    }

    public Employee find(String firstName, String lastName) {
        checkInputDetails(firstName, lastName);
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

    public void checkInputDetails(String firstname, String lastName) {
        if (!(StringUtils.isAlpha(firstname) && StringUtils.isAlpha(lastName))) {
            throw new InvalidInputException();
        }
    }

}
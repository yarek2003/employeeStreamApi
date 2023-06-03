package com.example.employeesstreamapi.exceptions;

public class EmployeeStorageIsFullException  extends RuntimeException{
    public EmployeeStorageIsFullException() {
    }

    public EmployeeStorageIsFullException(String message) {
        super(message);
    }
}

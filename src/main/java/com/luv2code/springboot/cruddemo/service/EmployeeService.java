package com.luv2code.springboot.cruddemo.service;

import com.luv2code.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    void save(Employee employee);
    List<Employee> findAll();
    Employee findById(int id);
    void update(int id, Employee employee);
    void deleteById(int id);
}

package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;
    // quick and dirty: inject employee dao

    @Autowired
    public EmployeeRestController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // expose "/employee" and return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable("employeeId") int id) {
        return employeeService.findById(id);
    }

    @PostMapping("/employees")
    public void save(@RequestBody Employee employee) {
        employeeService.save(employee);
    }

    @PutMapping("/employees/{employeeId}")
    public void update(@PathVariable("employeeId") int id, @RequestBody Employee employee) {
        employeeService.update(id, employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void delete(@PathVariable("employeeId") int id) {
        employeeService.delete(id);
    }
}

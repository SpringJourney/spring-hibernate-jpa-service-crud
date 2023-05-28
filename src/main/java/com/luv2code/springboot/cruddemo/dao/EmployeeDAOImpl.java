package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    // define field for entityManager
    EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOImpl(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Employee> findAll() {
        // create a query
        TypedQuery<Employee> typedQuery = entityManager.createQuery("FROM Employee", Employee.class);

        // return the query result list
        return typedQuery.getResultList();
    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public void update(int id, Employee employee) {
        TypedQuery<Employee> typedQuery = entityManager.createQuery("FROM Employee WHERE id=:theData", Employee.class);
//        Employee employeeOptional = entityManager.find(Employee.class, id);
        typedQuery.setParameter("theData", id);
        System.out.println(employee);
        Employee updatedEmployee = typedQuery.getSingleResult();
        if(updatedEmployee == null) {
            return;
        }
        if(employee.getFirstName() != null) {
            updatedEmployee.setFirstName(employee.getFirstName());
        }
        if(employee.getLastName() != null) {
            updatedEmployee.setLastName(employee.getLastName());
        }
        if(employee.getEmail() != null) {
            updatedEmployee.setEmail(employee.getEmail());
        }
        entityManager.merge(updatedEmployee);
    }

    @Override
    public void deleteById(int id) {
        Employee deleteEmployee = entityManager.find(Employee.class, id);
        entityManager.remove(deleteEmployee);
    }

    @Override
    public Employee save(Employee employee) {
        return entityManager.merge(employee);
    }
}

package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public void save(Employee employee) {

        switch(employee.getDesignation().toLowerCase()) {

        case "programmer":
            employee.setSalary(25000.0);
            break;

        case "manager":
            employee.setSalary(50000.0);
            break;

        case "tester":
            employee.setSalary(30000.0);
            break;
        }

        repository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee findByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    public void update(Employee employee) {
        repository.save(employee);
    }
    public boolean raiseSalary(String name,int percent)
    {
        Employee employee = repository.findByName(name).orElse(null);

        if(employee == null)
            return false;

        double salary = employee.getSalary();

        salary = salary + (salary * percent / 100);

        employee.setSalary(salary);

        repository.save(employee);

        return true;
    }
}

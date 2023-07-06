package com.israelsoto.springapisql.service;

import com.israelsoto.springapisql.model.Employee;
import com.israelsoto.springapisql.repository.IEmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private IEmployeeRepository employeeRepository;

    public EmployeeService(IEmployeeRepository iEmployeeRepository){
        this.employeeRepository = iEmployeeRepository;
    }

    public List<Employee> getAllEmployees(){
        Iterable<Employee> employees = employeeRepository.findAll();
        List<Employee> employeesList = new ArrayList<Employee>();

        employees.forEach(employee -> {
            employeesList.add(employee);
        });

        return employeesList;
    }
}

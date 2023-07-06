package com.israelsoto.springapisql.controller;

import com.israelsoto.springapisql.model.Employee;
import com.israelsoto.springapisql.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
         List<Employee> result = employeeService.getAllEmployees();
         return result;
    }
}

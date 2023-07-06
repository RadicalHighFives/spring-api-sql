package com.israelsoto.springapisql.repository;

import com.israelsoto.springapisql.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IEmployeeRepository extends JpaRepository<Employee, UUID> {
}

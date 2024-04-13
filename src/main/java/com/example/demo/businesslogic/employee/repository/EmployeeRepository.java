package com.example.demo.businesslogic.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.businesslogic.employee.domain.entity.Employee;
import java.util.List;
import java.util.UUID;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID>{
    List<Employee> findByName(String name);
}

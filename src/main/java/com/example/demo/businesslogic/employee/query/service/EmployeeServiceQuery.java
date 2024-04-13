package com.example.demo.businesslogic.employee.query.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.businesslogic.employee.domain.dto.EmployeeResponse;

public interface EmployeeServiceQuery {
    public List<EmployeeResponse> findByName(String name);
    public Page<EmployeeResponse> findEmployeeListByPage(int page, int pageSize);
}

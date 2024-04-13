package com.example.demo.businesslogic.employee.command.service;

import java.util.List;


import com.example.demo.businesslogic.employee.domain.dto.EmployeeRequestJson;

public interface EmployeeServiceCommand {
    public void createEmployeeByJsonObject(List<EmployeeRequestJson> employeeRequestJsonList);
    public void createEmployeeByCsv(String employeeRequestCsv);
}

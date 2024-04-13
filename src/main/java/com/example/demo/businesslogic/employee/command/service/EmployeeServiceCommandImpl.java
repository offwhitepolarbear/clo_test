package com.example.demo.businesslogic.employee.command.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.businesslogic.employee.domain.dto.EmployeeRequestJson;
import com.example.demo.businesslogic.employee.domain.entity.Employee;
import com.example.demo.businesslogic.employee.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeServiceCommandImpl implements EmployeeServiceCommand{

    private final EmployeeRepository employeeRepository;
    private final EmployeeRequestDataProcessor employeeRequestDataProcessor;
    @Override
    public void createEmployeeByJsonObject(List<EmployeeRequestJson> employeeRequestJsonList) {
        for(EmployeeRequestJson employeeRequestJson : employeeRequestJsonList){  
            Employee employee = employeeRequestDataProcessor.getEmployeeByEmployeeRequestJson(employeeRequestJson);
            employeeRepository.save(employee);
        }
    
    }
    
    @Override
    public void createEmployeeByCsv(String employeeRequestCsv) {
        List<Employee> employees = employeeRequestDataProcessor.parseEmployeeRequestCsv(employeeRequestCsv);

        for (Employee employee : employees){
            employeeRepository.save(employee);
        }

    }

}

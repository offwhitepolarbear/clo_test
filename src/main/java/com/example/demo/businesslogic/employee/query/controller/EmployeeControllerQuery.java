package com.example.demo.businesslogic.employee.query.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.businesslogic.employee.domain.dto.EmployeeResponse;
import com.example.demo.businesslogic.employee.query.service.EmployeeServiceQuery;
import com.example.demo.common.path.UrlPath;
import com.example.demo.common.swagger.ApiDescriptionAndSummary;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class EmployeeControllerQuery {

    private final EmployeeServiceQuery employeeServiceQuery;
    
    @Operation(summary = ApiDescriptionAndSummary.getEmployeeByNameSummary, description = ApiDescriptionAndSummary.getEmployeeByNameDescription)
    @GetMapping(UrlPath.getEmployeeByName)
    public ResponseEntity<List<EmployeeResponse>> getEmployeeByName(@PathVariable String name) {
        HttpStatus httpStatus = HttpStatus.ACCEPTED;
        return new ResponseEntity<>(employeeServiceQuery.findByName(name), httpStatus);
    }
    
    @Operation(summary = ApiDescriptionAndSummary.getEmployeeListByPageSummary, description = ApiDescriptionAndSummary.getEmployeeListByPageDescription)
    @GetMapping(UrlPath.getEmployeeListByPage)
    public ResponseEntity<Page<EmployeeResponse>> getEmployeeListByPage(int page, int pageSize) {
        HttpStatus httpStatus = HttpStatus.ACCEPTED;
        Page<EmployeeResponse> reponseBody = null;
        
        if (page < 1 || pageSize < 1){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        else{
            reponseBody = employeeServiceQuery.findEmployeeListByPage(page, pageSize);
        }

        return new ResponseEntity<>(reponseBody, httpStatus);
    }
    
}

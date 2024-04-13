package com.example.demo.businesslogic.employee.command.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.businesslogic.employee.command.service.EmployeeServiceCommand;
import com.example.demo.businesslogic.employee.domain.dto.EmployeeRequestJson;
import com.example.demo.common.path.UrlPath;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestController
public class EmployeeControllerCommand {
    
    private final EmployeeServiceCommand employeeServiceCommand;
    
    @Operation(summary="직원 입력 json 요청", description="정해진 형식의 json 요청으로 직원을 입력합니다.")
    @PostMapping(value = UrlPath.insertEmployees, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> employeeJsonRequest(@Validated @RequestBody List<EmployeeRequestJson> employeeRequestJson, BindingResult bindingResult) {
        HttpStatus httpStatus = HttpStatus.CREATED;
        
        if (bindingResult.hasErrors()){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        else{
            employeeServiceCommand.createEmployeeByJsonObject(employeeRequestJson);
        }

        return new ResponseEntity<>(httpStatus);
    }

    @Operation(summary="직원 입력 csv 요청", description="정해진 형식의 csv 요청으로 직원을 입력합니다.")
    @PostMapping(value = UrlPath.insertEmployees, consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> employeeCsvRequest(@RequestBody String employeeRequestCsv) {

        employeeServiceCommand.createEmployeeByCsv(employeeRequestCsv);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    
}       
 
package com.example.demo.businesslogic.employee.command.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.businesslogic.employee.domain.dto.EmployeeRequestJson;
import com.example.demo.businesslogic.employee.domain.entity.Employee;

@Service
public class EmployeeRequestDataProcessor {
    public Employee getEmployeeByEmployeeRequestJson(EmployeeRequestJson employeeRequestJson) {
        
        // 전화번호 - 기호 삭제 처리
        String tel = employeeRequestJson.getTel().replace("-", "");
        
        // date String localDateTime 변환
        String joined = employeeRequestJson.getJoined();
        LocalDateTime joinDateTime = parseStringToLocalDateTime(joined);
        
        Employee employee = Employee.builder()
                            .name(employeeRequestJson.getName())
                            .tel(tel)
                            .email(employeeRequestJson.getEmail())
                            .joined(joinDateTime)
                            .build();
        return employee;
    }

    private LocalDateTime parseStringToLocalDateTime(String dateString){
        // 날짜 스트링의 패턴에 따라 localDate 로 변환 후 localDateTime으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getDatePattern(dateString));
        LocalDate date = LocalDate.parse(dateString, formatter);
        LocalDateTime dateTime = date.atStartOfDay();
        return dateTime;
    }

    private String getDatePattern(String dateString){
        String datePattern = null;
        
        if (dateString.contains(".")){
            datePattern =  "yyyy.MM.dd";
        }
        if (dateString.contains("-")){
            datePattern = "yyyy-MM-dd";
        }

        return datePattern;
    }

    public List<Employee> parseEmployeeRequestCsv(String employeeRequestCsv){
        List<Employee> requestEmloyeeList = new ArrayList<Employee>();
        // csv 요청을 줄 단위로 분리
        String[] employeeRequestCsvLines = employeeRequestCsv.split("\\r?\\n");
        
        // 줄 단위로 분리된 요청을 줄 단위로 Employee로 변환 처리
        for (String employeeRequestCsvLine : employeeRequestCsvLines) {
            Employee employee = getEmployeeByEmployeeRequestCsvLine(employeeRequestCsvLine);
            requestEmloyeeList.add(employee);
        }

        return requestEmloyeeList;
    }

    private Employee getEmployeeByEmployeeRequestCsvLine(String employeeRequestCsvLine){
        Employee employee = null;

        // 파싱 기준 , 뒤에 공백 한칸 추가되어있음
        final String csvSplitPattern = ", ";

        String[] employeeRequestCsvLineValues = employeeRequestCsvLine.split(csvSplitPattern);

         // value 유효성 검사
         if (validateEmployeeRequestCsvString(employeeRequestCsvLineValues)){
            
            // csv 에서 개별 value 값 추출 
            String name = employeeRequestCsvLineValues[0];
            String mail = employeeRequestCsvLineValues[1];
            String tel = employeeRequestCsvLineValues[2];
            String joined = employeeRequestCsvLineValues[3];
            LocalDateTime joinLocalDateTime = parseStringToLocalDateTime(joined);
            
            // 개별 값에 대한 처리를 포함 employee 형태로 변경
            employee = Employee.builder()
                                .name(name)
                                .email(mail)
                                .tel(tel)
                                .joined(joinLocalDateTime)
                                .build();
         }

        return employee;
    }

    private boolean validateEmployeeRequestCsvString(String[] employeeRequestCsvLineValues){
        boolean validatedRequest = false;
        
        // csv 유효성 검사 ,로 분리한 사이즈 검사
        boolean sizeValidated = employeeRequestCsvLineValues.length == 4;
        if (sizeValidated){
            validatedRequest = true;
        }

        return validatedRequest;
    }

}
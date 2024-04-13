package com.example.demo.businesslogic.employee.command.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.businesslogic.employee.command.service.EmployeeRequestFileUploadService;
import com.example.demo.common.path.UrlPath;
import com.example.demo.common.swagger.ApiDescriptionAndSummary;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class EmployeeControllerCommandFileUpload {
    private final EmployeeRequestFileUploadService employeeRequestFileUploadService;

    @Operation(summary = ApiDescriptionAndSummary.insertEmployeesSummary, description = ApiDescriptionAndSummary.insertEmployeesDescription)
    @PostMapping(value = UrlPath.insertEmployees, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> employeeFileUpload(@RequestParam("file") MultipartFile file) {

        String responseMessage = null;
        HttpStatus httpStatus = HttpStatus.CREATED;

        // 허용되는 파일 컨텐츠 타입
        List<String> allowedContentTypeList = Arrays.asList("text/csv", MediaType.APPLICATION_JSON_VALUE);    
        
        if (!allowedContentTypeList.contains(file.getContentType())) {
            responseMessage = "Invalid file type : " + file.getContentType();
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        else{
            employeeRequestFileUploadService.uploadEmployeeFile(file);
        }

        return new ResponseEntity<>(responseMessage, httpStatus);
    }
}

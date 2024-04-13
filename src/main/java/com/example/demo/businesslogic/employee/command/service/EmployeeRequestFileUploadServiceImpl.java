package com.example.demo.businesslogic.employee.command.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.businesslogic.employee.domain.dto.EmployeeRequestJson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeRequestFileUploadServiceImpl implements EmployeeRequestFileUploadService{

    private final EmployeeServiceCommand employeeServiceCommand;

    @Override
    public void uploadEmployeeFile(MultipartFile file) {
        
        boolean isCsvFile = file.getContentType().equals("text/csv");
        boolean isJsonFile = file.getContentType().equals("application/json");
        
        if (isCsvFile){
            String csvString = parseCsvFileToString(file);
            employeeServiceCommand.createEmployeeByCsv(csvString);
        }

        if (isJsonFile){
            List<EmployeeRequestJson> employeeRequestJsonList= parseJsonFileToJsonObject(file);
            employeeServiceCommand.createEmployeeByJsonObject(employeeRequestJsonList);
        }  
    }

    // json파일 파싱
    public List<EmployeeRequestJson> parseJsonFileToJsonObject(MultipartFile file) {
        List<EmployeeRequestJson> employeeRequestJsons = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = file.getInputStream()) {
            employeeRequestJsons = objectMapper.readValue(inputStream, new TypeReference<List<EmployeeRequestJson>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeRequestJsons;
    }

    // csv파일 파싱 
    public String parseCsvFileToString(MultipartFile file) {
        String lines = "";
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"))) {
            
            String line;
            while ((line = bufferedReader.readLine()) !=null){

                // 첫 줄인 경우 줄바꿈 삽입 안하게
                if (lines.equals("")){
                    lines += line;
                }
                else{
                    lines = lines + "\n" + line;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

}

package com.example.demo.businesslogic.employee.command.service;

import org.springframework.web.multipart.MultipartFile;

public interface EmployeeRequestFileUploadService {
    public void uploadEmployeeFile(MultipartFile file);
}

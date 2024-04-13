package com.example.demo.businesslogic.employee.query.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.businesslogic.employee.domain.dto.EmployeeResponse;
import com.example.demo.businesslogic.employee.domain.entity.Employee;
import com.example.demo.businesslogic.employee.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeSerivceQueryImpl implements EmployeeServiceQuery{

    private final EmployeeRepository employeeRepository;

    @Override
    public Page<EmployeeResponse> findEmployeeListByPage(int page, int pageSize) {

        // jpa pageable을 통한 페이지네이션이 기본적으로 페이지 0부터 카운팅됨
        // client의 페이지 요청은 1부터 시작되기 때문에 request에서 넘어온 pageNumber를 1차감
        // 응답에서 pageNumber 복구 처리가 상당히 곤란하게 동작하기 때문에 응답에 대한 처리는 하지 않고 리턴 (프론트에 고지 필요)
        final Pageable pageable = PageRequest.of(page-1, pageSize);
        
        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        Page<EmployeeResponse> employeeResponsPage = getPageEmployeeResponseByPageEmployee(employeePage);
        
        return employeeResponsPage;
    }

    @Override
    public List<EmployeeResponse> findByName(String name) {
        
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        
        List<Employee> employeeList = employeeRepository.findByName(name);
        
        for (Employee employee : employeeList){
            EmployeeResponse employeeResponse = getEmployeeResponseByEmployeeEntity(employee);
            employeeResponseList.add(employeeResponse);
        }
        
        return employeeResponseList;
    }
    
    private Page<EmployeeResponse> getPageEmployeeResponseByPageEmployee(Page<Employee> employeePage){
        
        // 응답 dto에 맞는 데이터로 변경 처리
        List<Employee> employeeList = employeePage.getContent();
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        for (Employee employee : employeeList){
            EmployeeResponse employeeResponse = getEmployeeResponseByEmployeeEntity(employee);
            employeeResponseList.add(employeeResponse);
        }

        Long totalElementSize = employeePage.getTotalElements();

        // 0 부터 조회하는 jpa pageable 페이지 네이션 관련 선처리에 입력된 pageNumber에 대한 후속 처리 
        // 최종 페이지 관련 로직 처리가 안되서 처리하지 않고 현재 페이지 수를 그대로 리턴
        // Pageable pageable = employeePage.getPageable().withPage(employeePage.getPageable().getPageNumber() + 1);
        Page<EmployeeResponse> newEmployeeResponsePage = new PageImpl<>(employeeResponseList, employeePage.getPageable(), totalElementSize);

        return newEmployeeResponsePage;
    }

    


    private EmployeeResponse getEmployeeResponseByEmployeeEntity(Employee employee){

        EmployeeResponse employeeResponse = EmployeeResponse.builder()
                                                .name(employee.getName())
                                                .email(employee.getEmail())
                                                .tel(employee.getTel())
                                                .joined(employee.getJoined().toLocalDate())
                                                .build();
        return employeeResponse;
    }
}

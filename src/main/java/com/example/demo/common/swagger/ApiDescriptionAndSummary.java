package com.example.demo.common.swagger;

public class ApiDescriptionAndSummary {
    public static final String insertEmployeesSummary ="employee 등록을 위한 파일업로드 기능"; 
    public static final String insertEmployeesDescription ="정해진 형식으로 파일에 입력된 직원 정보를 db에 저장합니다 .csv/.json 파일을 지원합니다"; 

    public static final String getEmployeeByNameSummary ="직원 이름 검색"; 
    public static final String getEmployeeByNameDescription ="해당 이름을 가진 직원의 연락처를 조회합니다."; 

    public static final String getEmployeeListByPageSummary ="직원 목록 검색"; 
    public static final String getEmployeeListByPageDescription ="입력된 페이지에 해당하는 직원들의 연락처를 목록으로 조회합니다"; 


}

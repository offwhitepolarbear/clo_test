package com.example.demo.common.path;

public class UrlPath {

    private static final String apiPathPrefix = "/api";
    
    // Query Url 
    public static final String getEmployeeByName = apiPathPrefix + "/employee/{name}";
    public static final String getEmployeeListByPage = apiPathPrefix + "/employee";
    
    // Command Url
    public static final String insertEmployees = apiPathPrefix + "/employee";
}

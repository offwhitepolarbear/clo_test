package com.example.demo.businesslogic.employee.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestJson {
    
    @ApiModelProperty(name = "이름", example = "홍길동")
    private String name;
    @ApiModelProperty(name = "이메일", example = "kildong.hong@clovf.com")
    private String email;
    @ApiModelProperty(name = "전화번호", example = "010-1234-1234")
    private String tel;
    @ApiModelProperty(name = "입사일", example = "2015-08-15")
    private String joined;
}

package com.example.demo.businesslogic.employee.domain.dto;

import java.time.LocalDate;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponse {

    @ApiModelProperty(name = "이름", example = "홍길동")
    private String name;
    @ApiModelProperty(name = "이메일", example = "kildong.hong@clovf.com")
    private String email;
    @ApiModelProperty(name = "전화번호", example = "01012341234")
    private String tel;
    @ApiModelProperty(name = "입사일", example = "2015-08-15")
    private LocalDate joined;
    
    @Builder
    public EmployeeResponse(String name, String email, String tel, LocalDate joined){
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.joined = joined;
    }
}

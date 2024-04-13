package com.example.demo.businesslogic.employee.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.example.demo.common.entity.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = Employee.tableName)
public class Employee extends BaseTimeEntity{
    
    // 테이블 명칭
    public static final String tableName = "employee";
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    
    private String name;
    private String email;
    private String tel;
    private LocalDateTime joined;

    @Builder
    public Employee(String name, String email, String tel, LocalDateTime joined){
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.joined = joined;
    }

}

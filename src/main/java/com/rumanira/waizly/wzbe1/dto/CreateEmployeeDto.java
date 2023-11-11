package com.rumanira.waizly.wzbe1.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rumanira.waizly.wzbe1.entity.Employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEmployeeDto {
    private String name;
    @JsonProperty("job_title")
    private String jobTitle;
    private int salary;
    private String department;
    @JsonProperty("joined_date")
    private String joinedDate;

    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setName(this.name);
        employee.setJobTitle(this.jobTitle);
        employee.setSalary(this.salary);
        employee.setDepartment(this.department);
        employee.setJoinedDate(LocalDate.parse(this.joinedDate));
        return employee;
    }
}

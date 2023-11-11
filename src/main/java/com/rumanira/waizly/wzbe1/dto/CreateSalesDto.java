package com.rumanira.waizly.wzbe1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rumanira.waizly.wzbe1.entity.Employee;
import com.rumanira.waizly.wzbe1.entity.Sales;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSalesDto {
    @JsonProperty("employee_id")
    private Long employeeId;
    private Integer sales;

    public Sales toSales() {
        Employee employee = new Employee();
        employee.setEmployeeId(this.employeeId);
        Sales sales = new Sales();
        sales.setEmployee(employee);
        sales.setSales(this.sales);
        return sales;
    }
}

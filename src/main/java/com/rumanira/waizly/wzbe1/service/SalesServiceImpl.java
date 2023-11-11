package com.rumanira.waizly.wzbe1.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rumanira.waizly.wzbe1.entity.Employee;
import com.rumanira.waizly.wzbe1.entity.Sales;
import com.rumanira.waizly.wzbe1.repository.EmployeeRepository;
import com.rumanira.waizly.wzbe1.repository.SalesRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
public class SalesServiceImpl implements SalesService{
    private SalesRepository salesRepository;
    private EmployeeRepository employeeRepository;
    public SalesServiceImpl(SalesRepository salesRepository, EmployeeRepository employeeRepository) {
        this.salesRepository = salesRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    @Override
    @Transactional
    public Sales addSales(Sales sales) {
        Employee employee = employeeRepository.findById(sales.getEmployee().getEmployeeId())
        .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        sales.setEmployee(employee);
        return salesRepository.save(sales);
    }
    
}

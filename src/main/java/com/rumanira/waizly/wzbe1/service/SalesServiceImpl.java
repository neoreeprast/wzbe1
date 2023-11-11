package com.rumanira.waizly.wzbe1.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rumanira.waizly.wzbe1.entity.Sales;
import com.rumanira.waizly.wzbe1.repository.SalesRepository;

@Service
@Transactional(readOnly = true)
public class SalesServiceImpl implements SalesService{
    private SalesRepository salesRepository;
    public SalesServiceImpl(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @Override
    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    @Override
    @Transactional
    public Sales addSales(Sales sales) {
        return salesRepository.save(sales);
    }
    
}

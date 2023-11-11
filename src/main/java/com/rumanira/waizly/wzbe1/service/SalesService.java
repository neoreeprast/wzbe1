package com.rumanira.waizly.wzbe1.service;

import java.util.List;

import com.rumanira.waizly.wzbe1.entity.Sales;

public interface SalesService {
    List<Sales> getAllSales();
    Sales addSales(Sales sales);
}

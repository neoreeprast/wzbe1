package com.rumanira.waizly.wzbe1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rumanira.waizly.wzbe1.dto.CreateSalesDto;
import com.rumanira.waizly.wzbe1.entity.Sales;
import com.rumanira.waizly.wzbe1.service.SalesService;

@RestController
@RequestMapping("sales")
public class SalesController {
    private SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping
    public List<Sales> getAllSales() {
        return salesService.getAllSales();
    }

    @PostMapping
    public ResponseEntity<Sales> addSales(@RequestBody CreateSalesDto sales) {
        Sales savedSales = salesService.addSales(sales.toSales());
        return new ResponseEntity<>(savedSales, HttpStatus.CREATED);
    }
}

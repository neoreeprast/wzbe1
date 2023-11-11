package com.rumanira.waizly.wzbe1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rumanira.waizly.wzbe1.entity.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
    
}

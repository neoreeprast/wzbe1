package com.rumanira.waizly.wzbe1.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="sales_data")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sales_id")
    private Long salesId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private Employee employee;
    
    @Column(name="sales")
    private int sales;
    
    @Column(name="created_at")
    private LocalDateTime createdAt;
    
    // Constructors, getters, and setters
}

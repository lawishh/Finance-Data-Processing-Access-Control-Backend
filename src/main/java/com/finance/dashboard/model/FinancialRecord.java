package com.finance.dashboard.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "financial_records")
public class FinancialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String type; // INCOME / EXPENSE
    private String category;
    private LocalDate recordDate;
    private String description;

    public Long getId() { return id; }
    public Double getAmount() { return amount; }
    public String getType() { return type; }
    public String getCategory() { return category; }
    public LocalDate getRecordDate() { return recordDate; }
    public String getDescription() { return description; }

    public void setId(Long id) { this.id = id; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setType(String type) { this.type = type; }
    public void setCategory(String category) { this.category = category; }
    public void setRecordDate(LocalDate recordDate) { this.recordDate = recordDate; }
    public void setDescription(String description) { this.description = description; }
}
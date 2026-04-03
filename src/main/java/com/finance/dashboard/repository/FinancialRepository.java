package com.finance.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.finance.dashboard.model.FinancialRecord;

import java.util.List;

public interface FinancialRepository extends JpaRepository<FinancialRecord, Long> {

    @Query("SELECT COALESCE(SUM(f.amount),0) FROM FinancialRecord f WHERE f.type='INCOME'")
    Double getTotalIncome();

    @Query("SELECT COALESCE(SUM(f.amount),0) FROM FinancialRecord f WHERE f.type='EXPENSE'")
    Double getTotalExpense();

    List<FinancialRecord> findByType(String type);
    List<FinancialRecord> findByCategory(String category);
}
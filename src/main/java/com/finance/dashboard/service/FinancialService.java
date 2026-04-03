package com.finance.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.dashboard.model.FinancialRecord;
import com.finance.dashboard.model.Role;
import com.finance.dashboard.repository.FinancialRepository;

import java.util.List;

@Service
public class FinancialService {

    @Autowired
    private FinancialRepository repo;

    public FinancialRecord create(FinancialRecord record, Role role) {

    if (role != Role.ADMIN) {
        throw new RuntimeException("Access Denied");
    }

    // ✅ FIX: normalize type
    if (record.getType() != null) {
        record.setType(record.getType().toUpperCase());
    }

    return repo.save(record);
}
//viewer can only see, editor can see and edit, admin can do all
     public List<FinancialRecord> getAll(Role role) {
        if (role == Role.VIEWER) {
            throw new RuntimeException("Access Denied");
        }
        return repo.findAll();
    }

    public FinancialRecord update(Long id, FinancialRecord record, Role role) {
        if (role != Role.ADMIN) {
            throw new RuntimeException("Access Denied");
        }

        FinancialRecord existing = repo.findById(id).orElseThrow();
        existing.setAmount(record.getAmount());
        existing.setType(record.getType());
        existing.setCategory(record.getCategory());
        existing.setRecordDate(record.getRecordDate());
        existing.setDescription(record.getDescription());

        return repo.save(existing);
    }

    public void delete(Long id, Role role) {
        if (role != Role.ADMIN) {
            throw new RuntimeException("Access Denied");
        }
        repo.deleteById(id);
    }

    public Double getIncome() {
        return repo.getTotalIncome();
    }

    public Double getExpense() {
        return repo.getTotalExpense();
    }

    public Double getBalance() {
        return getIncome() - getExpense();
    }

    public List<FinancialRecord> filterByType(String type) {
        return repo.findByType(type);
    }

    public List<FinancialRecord> filterByCategory(String category) {
        return repo.findByCategory(category);
    }
}
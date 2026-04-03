package com.finance.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.finance.dashboard.model.FinancialRecord;
import com.finance.dashboard.model.Role;
import com.finance.dashboard.service.FinancialService;

import java.util.*;

@RestController
@RequestMapping("/api/finance")
@CrossOrigin("*")
public class FinancialController {

    @Autowired
    private FinancialService service;

    private Role getRole(String role) {
        return Role.valueOf(role.toUpperCase());
    }

    @PostMapping("/create")
    public FinancialRecord create(@RequestBody FinancialRecord record,
                                  @RequestHeader String role) {
        return service.create(record, getRole(role));
    }

    @GetMapping("/all")
    public List<FinancialRecord> getAll(@RequestHeader String role) {
        return service.getAll(getRole(role));
    }

    @PutMapping("/update/{id}")
    public FinancialRecord update(@PathVariable Long id,
                                 @RequestBody FinancialRecord record,
                                 @RequestHeader String role) {
        return service.update(id, record, getRole(role));
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         @RequestHeader String role) {
        service.delete(id, getRole(role));
        return "Deleted";
    }

    @GetMapping("/summary")
    public Map<String, Double> summary() {
        Map<String, Double> map = new HashMap<>();
        map.put("income", service.getIncome());
        map.put("expense", service.getExpense());
        map.put("balance", service.getBalance());
        return map;
    }

    @GetMapping("/filter/type")
    public List<FinancialRecord> filterType(@RequestParam String type) {
        return service.filterByType(type);
    }

    @GetMapping("/filter/category")
    public List<FinancialRecord> filterCategory(@RequestParam String category) {
        return service.filterByCategory(category);
    }
}
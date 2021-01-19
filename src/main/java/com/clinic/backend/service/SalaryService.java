package com.clinic.backend.service;

import com.clinic.backend.entity.Salary;
import com.clinic.backend.repository.SalaryRepository;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SalaryService implements Dao<Salary>{
    private final SalaryRepository salaryRepository;

    public SalaryService(SalaryRepository salaryRepository) {
        this.salaryRepository = salaryRepository;
    }

    public List<Salary> getSalaries() {
        return salaryRepository.findAll();
    }

    @Override
    public Salary save(Salary salary) {
        return salaryRepository.save(salary);
    }

    @Override
    public Salary update(Salary salary) {
        return salaryRepository.save(salary);
    }

    @Override
    public void delete(Salary salary) {
        salaryRepository.delete(salary);
    }

    @Override
    public Optional<Salary> get(Integer id) {
        return salaryRepository.findById(id);
    }

    @Override
    public List<Salary> findAll() {
        return null;
    }

    @Override
    public List<Salary> findAll(Integer id) {
        return null;
    }

    public Salary checkIfPayed(Integer id, Integer month) {
        return salaryRepository.checkIfPayed(id, month);
    }

    //public void insertEntry(Integer id) {
    //    salaryRepository.insertEntry(id);
    //}
}

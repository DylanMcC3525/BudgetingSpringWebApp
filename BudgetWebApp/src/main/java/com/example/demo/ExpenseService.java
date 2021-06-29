package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExpenseService {

    @Autowired
    private ExpenseRepository repo;

    public List<Expense> listAll() {
        return (List<Expense>) repo.findAll();
    }

    public void save(Expense expense) {
        repo.save(expense);
    }


    public Expense get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

    public List<Expense> findByAuthorId(long authorId) {
        return repo.findByAuthorId(authorId);
    }
}
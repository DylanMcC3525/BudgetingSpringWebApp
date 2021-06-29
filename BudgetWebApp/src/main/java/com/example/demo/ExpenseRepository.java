package com.example.demo;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;;import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense,Long> {
    public List<Expense> findByAuthorId(long authorId);

}

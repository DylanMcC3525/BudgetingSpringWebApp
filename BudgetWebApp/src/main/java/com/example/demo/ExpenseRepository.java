package com.example.demo;


import org.springframework.data.repository.CrudRepository;;


public interface ExpenseRepository extends CrudRepository<Expense,Long> {

}

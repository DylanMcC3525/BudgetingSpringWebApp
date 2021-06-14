package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ExpenseRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ExpenseRepository repo;

    // test methods go below
    @Test
    public void testCreateExpense() {
        Expense expense = new Expense();
        expense.setExpense("Gas");
        expense.setExpenseAmount(2004.1);


        Expense savedExpense = repo.save(expense);


    }

}



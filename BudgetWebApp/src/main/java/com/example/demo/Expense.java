package com.example.demo;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="expense",nullable = false, length = 45)
    public String label;


    //@Column(name="author_id", nullable = false, length=45)
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName="id")
    public User author;

    @Column(name = "expense_amount", nullable = false, length = 64)
    public double expenseAmount;

//    Getters & Setters
public Long getId() {
    return id;
}

    public void setId(Long id) {
        this.id = id;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }




}
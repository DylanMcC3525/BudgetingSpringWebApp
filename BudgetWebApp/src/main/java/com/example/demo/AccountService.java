package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository repo;

    public List<Account> listAll() {
        return (List<Account>) repo.findAll();
    }

    public void save(Account account) {
        repo.save(account);
    }


    public Account get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }


    public List<Account> findByUserId(long userID){
        return repo.findByUserId(userID);
    }

    public void addDepositToUserAccount(Long userID, int balance) {
        Account currUserAcc = (Account) repo.findByUserId(userID);
        currUserAcc.setBalance(currUserAcc.getBalance() + balance);
        repo.save(currUserAcc);
    }

}


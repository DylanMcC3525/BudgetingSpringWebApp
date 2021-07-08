package com.example.demo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;;import java.util.List;


public interface AccountRepository extends CrudRepository<Account,Long> {
    public List<Account> findByUserId(long userID);

}

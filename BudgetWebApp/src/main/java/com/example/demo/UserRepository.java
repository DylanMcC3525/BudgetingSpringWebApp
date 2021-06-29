package com.example.demo;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

        @Query("SELECT u FROM User u WHERE u.email = ?1")
        public User findByEmail(String email);


//        @Query("SELECT * FROM User WHERE u.id = ?1")
//        public List<User> findByUserID(long userID);
//
//        @Query("SELECT * FROM expenses WHERE u.author_id = ?1")
//        public Expense findExpenseByAuthor();

//

}

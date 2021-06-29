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
public class AccountRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository repo;

    // test methods go below
    @Test
    public void testCreateUser() {
        Account account = new Account();
        account.setAccountType("Checking");
        account.setBalance(9000);

        Account savedAccount = repo.save(account);

    }

}



package com.java.domain.repository;

import com.java.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByAccountNumber(String accountNumber);

    boolean existsByCardNumber(String accountNumber);
}

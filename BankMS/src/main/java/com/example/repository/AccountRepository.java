package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Account;
import com.example.model.Client;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
 Account findById(Integer id);
 List<Account>  findByClient(Client c);
 @Transactional
 void deleteById(Integer id);
}

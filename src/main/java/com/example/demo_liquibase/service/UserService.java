package com.example.demo_liquibase.service;

import com.example.demo_liquibase.model.entity.Transaction;
import com.example.demo_liquibase.model.entity.User;
import com.example.demo_liquibase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionService transactionService;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<Transaction> getUserTransactions(Integer userId) {
        return transactionService.findAllByUserId(userId);
    }
}
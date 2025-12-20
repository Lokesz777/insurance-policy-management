package com.example.main.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.main.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

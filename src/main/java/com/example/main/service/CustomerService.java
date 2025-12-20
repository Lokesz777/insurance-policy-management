package com.example.main.service;

import com.example.main.model.Customer;
import com.example.main.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }
    //???
    //

    public List<Customer> getAll() { return repo.findAll(); }
    public Customer getById(Long id) { return repo.findById(id).orElseThrow(); }
    public Customer save(Customer c) { return repo.save(c); }
    public Customer update(Long id, Customer data) {
        Customer existing = getById(id);
        existing.setName(data.getName());
        existing.setEmail(data.getEmail());
        existing.setPhone(data.getPhone());
        existing.setAddress(data.getAddress());
        //
        return repo.save(existing);
        //
    }
}

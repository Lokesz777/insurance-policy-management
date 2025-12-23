package com.example.main.service;

import com.example.main.model.Policy;
import com.example.main.repository.PolicyRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PolicyService {
    private final PolicyRepository repo;

    public PolicyService(PolicyRepository repo) {
        this.repo = repo;
    }

    public List<Policy> getAll() { return repo.findAll(); }
    public Policy getById(Long id) { return repo.findById(id).orElseThrow(); }
    public Policy save(Policy p) { return repo.save(p); }
    public Policy update(Long id, Policy data) {
        data.setPolicyId(id);
        return repo.save(data);
    }
    public void delete(Long id) { repo.deleteById(id); }
}

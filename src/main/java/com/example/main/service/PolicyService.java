//package com.example.main.service;
//
//import com.example.main.model.Policy;
//import com.example.main.model.Customer;
//import com.example.main.repository.PolicyRepository;
//import com.example.main.repository.CustomerRepository;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//@Service
//public class PolicyService {
//    private final PolicyRepository policyRepo;
//    private final CustomerRepository customerRepo;
//
//    public PolicyService(PolicyRepository policyRepo, CustomerRepository customerRepo) {
//        this.policyRepo = policyRepo;
//        this.customerRepo = customerRepo;
//    }
//
//    public Policy create(Long customerId, Policy p) {
//        Customer c = customerRepo.findById(customerId).orElseThrow();
//        p.setCustomer(c);
//        return policyRepo.save(p);
//    }
//
//    public Policy update(Long id, Policy data) {
//        Policy existing = policyRepo.findById(id).orElseThrow();
//        existing.setPolicyType(data.getPolicyType());
//        existing.setCoverageAmount(data.getCoverageAmount());
//        existing.setPremiumAmount(data.getPremiumAmount());
//        existing.setValidityStartDate(data.getValidityStartDate());
//        existing.setValidityEndDate(data.getValidityEndDate());
//        return policyRepo.save(existing);
//    }
//
//    public Policy getById(Long id) { return policyRepo.findById(id).orElseThrow(); }
//    public void delete(Long id) { policyRepo.deleteById(id); }
//    public List<Policy> getByCustomer(Long customerId) {
//        return customerRepo.findById(customerId).orElseThrow().getPolicies();
//    }
//}

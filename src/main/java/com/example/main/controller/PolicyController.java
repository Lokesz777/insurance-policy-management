package com.example.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.main.model.Policy;
import com.example.main.model.Customer;
import com.example.main.service.CustomerService;
import com.example.main.service.PolicyService;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/policies")
public class PolicyController {
    private final PolicyService policyService;
    private final CustomerService customerService;

    @Autowired
    public PolicyController(PolicyService policyService, CustomerService customerService) {
        this.policyService = policyService;
        this.customerService = customerService;
    }

    private List<Customer> getAvailableCustomers(Long currentPolicyId) {
        List<Customer> allCustomers = customerService.getAll();
        List<Long> mappedCustomerIds = policyService.getAll().stream()
            .filter(p -> currentPolicyId == null || !p.getPolicyId().equals(currentPolicyId))
            .filter(p -> p.getCustomer() != null)
            .map(p -> p.getCustomer().getCustomerId())
            .collect(Collectors.toList());
        
        return allCustomers.stream()
            .filter(c -> !mappedCustomerIds.contains(c.getCustomerId()))
            .collect(Collectors.toList());
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("policies", policyService.getAll());
        return "policy-list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("policy", new Policy());
        model.addAttribute("customers", getAvailableCustomers(null));
        return "policy-form";
    }

    @PostMapping
    public String save(@ModelAttribute Policy policy) {
        if (policy.getCustomer() != null && policy.getCustomer().getCustomerId() != null) {
            policy.setCustomer(customerService.getById(policy.getCustomer().getCustomerId()));
        }
        policyService.save(policy);
        return "redirect:/policies";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Policy policy = policyService.getById(id);
        model.addAttribute("policy", policy);
        model.addAttribute("customers", getAvailableCustomers(id));
        return "policy-form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Policy policy) {
        if (policy.getCustomer() != null && policy.getCustomer().getCustomerId() != null) {
            policy.setCustomer(customerService.getById(policy.getCustomer().getCustomerId()));
        }
        policyService.update(id, policy);
        return "redirect:/policies";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        policyService.delete(id);
        return "redirect:/policies";
    }

    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        policyService.delete(id);
        return "redirect:/policies";
    }
}

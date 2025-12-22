package com.example.main.controller;

import com.example.main.model.Policy;
import com.example.main.service.PolicyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/policies")
public class PolicyController {
    private final PolicyService service;

    public PolicyController(PolicyService service) { this.service = service; }

    // List policies for a customer
    @GetMapping("/customer/{customerId}")
    public String listByCustomer(@PathVariable Long customerId, Model model) {
        model.addAttribute("policies", service.getByCustomer(customerId));
        model.addAttribute("customerId", customerId);
        return "policy-list";
    }

    // Show policy details
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("policy", service.getById(id));
        return "policy-details";
    }

    // Show add form
    @GetMapping("/customer/{customerId}/new")
    public String form(@PathVariable Long customerId, Model model) {
        model.addAttribute("policy", new Policy());
        model.addAttribute("customerId", customerId);
        return "policy-form";
    }

    // Save new policy
    @PostMapping("/customer/{customerId}")
    public String save(@PathVariable Long customerId, @ModelAttribute Policy policy) {
        service.create(customerId, policy);
        return "redirect:/policies/customer/" + customerId;
    }

    // Show edit form
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Policy policy = service.getById(id);
        model.addAttribute("policy", policy);
        model.addAttribute("customerId", policy.getCustomer().getCustomerId()); // add customerId
        return "policy-form";
    }

    // Update policy
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Policy policy) {
        service.update(id, policy);
        return "redirect:/policies/" + id;
    }

    // Delete policy
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        Long customerId = service.getById(id).getCustomer().getCustomerId();
        service.delete(id);
        return "redirect:/policies/customer/" + customerId;
    }
}

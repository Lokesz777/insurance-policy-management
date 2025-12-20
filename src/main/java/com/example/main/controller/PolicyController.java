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

    @GetMapping("/customer/{customerId}")
    public String listByCustomer(@PathVariable Long customerId, Model model) {
        model.addAttribute("policies", service.getByCustomer(customerId));
        model.addAttribute("customerId", customerId);
        return "policy-list";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("policy", service.getById(id));
        return "policy-details";
    }

    @GetMapping("/customer/{customerId}/new")
    public String form(@PathVariable Long customerId, Model model) {
        model.addAttribute("policy", new Policy());
        model.addAttribute("customerId", customerId);
        return "policy-form";
    }

    @PostMapping("/customer/{customerId}")
    public String save(@PathVariable Long customerId, @ModelAttribute Policy policy) {
        service.create(customerId, policy);
        return "redirect:/policies/customer/" + customerId;
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("policy", service.getById(id));
        return "policy-form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Policy policy) {
        service.update(id, policy);
        return "redirect:/policies/" + id;
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        Long customerId = service.getById(id).getCustomer().getCustomerId();
        service.delete(id);
        return "redirect:/policies/customer/" + customerId;
    }
}

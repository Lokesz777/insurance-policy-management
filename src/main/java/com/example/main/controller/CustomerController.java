package com.example.main.controller;

import com.example.main.model.Customer;
import com.example.main.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service)
    {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("customers", service.getAll());
        return "customer-list";
    }

//    @GetMapping("/{id}")
//    public String details(@PathVariable Long id, Model model) {
//        model.addAttribute("customer", service.getById(id));
//        return "customer-details";
//    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @PostMapping
    public String save(@ModelAttribute Customer customer) {
        service.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("customer", service.getById(id));
        return "customer-form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, Customer customer) {
        service.update(id, customer);
        return "redirect:/customers";
    }
}

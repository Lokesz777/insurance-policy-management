package com.example.main.model;


import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="policy_id")
    private Long policyId;
@Column(name="policy_type")
    private String policyType;
@Column(name="coverage_amount")
    private BigDecimal coverageAmount;
@Column(name="premium_amount")
    private BigDecimal premiumAmount;
@Column(name="validity_start_date")
    private LocalDate validityStartDate;
@Column(name="validity_end_date")
    private LocalDate validityEndDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // Getters and Setters

    public Long getPolicyId() {
        return policyId;
    }

    public BigDecimal getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(BigDecimal coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public BigDecimal getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(BigDecimal premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public LocalDate getValidityEndDate() {
        return validityEndDate;
    }

    public void setValidityEndDate(LocalDate validityEndDate) {
        this.validityEndDate = validityEndDate;
    }

    public LocalDate getValidityStartDate() {
        return validityStartDate;
    }

    public void setValidityStartDate(LocalDate validityStartDate) {
        this.validityStartDate = validityStartDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }
}

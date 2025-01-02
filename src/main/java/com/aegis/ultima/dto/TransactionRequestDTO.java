package com.aegis.ultima.dto;

import java.math.BigDecimal;
import java.util.List;

public class TransactionRequestDTO {

    private String status;

    private BigDecimal totalAmount;

    private String remarks;

    private List<TransactionDetailRequestDTO> transactionDetails;

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<TransactionDetailRequestDTO> getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(List<TransactionDetailRequestDTO> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }
}


package com.aegis.ultima.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transaksi")
public class Transaction {

    @Id
    @Column(name = "id", nullable = false, length = 255)
    private String id;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "total_harga", precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "remarks", nullable = true)
    private String remarks;

    @Column(name = "created_at", nullable = true)
    private Date createdAt;

    @Column(name = "created_by", nullable = true, length = 255)
    private String createdBy;

    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    @Column(name = "updated_by", nullable = true, length = 255)
    private String updatedBy;

    @Column(name = "deleted_at", nullable = true)
    private Date deletedAt;

    @Column(name = "deleted_by", nullable = true, length = 255)
    private String deletedBy;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }
}


package com.aegis.ultima.repository;

import com.aegis.ultima.model.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetails, String> {

    List<TransactionDetails> findTransactionDetailsByTransactionId(String id);
}

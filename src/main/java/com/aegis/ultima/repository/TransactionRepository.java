package com.aegis.ultima.repository;

import com.aegis.ultima.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    Transaction findTransactionById(String id);

//    @Query("SELECT t FROM Transaction t WHERE t.createdAt BETWEEN :startDate AND :endDate")
//    List<Transaction> findTransactionsByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "SELECT * FROM transaksi t WHERE t.created_at::date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Transaction> findTransactionsByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}

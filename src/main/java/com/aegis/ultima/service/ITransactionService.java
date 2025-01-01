package com.aegis.ultima.service;

import com.aegis.ultima.model.TransactionRequestDTO;
import com.aegis.ultima.model.TransactionResponseDTO;
import com.aegis.ultima.util.BaseClassDomain;

import java.util.Date;
import java.util.List;

public interface ITransactionService {

    BaseClassDomain<TransactionRequestDTO> createTransaction(TransactionRequestDTO transactionRequest);
    BaseClassDomain<TransactionResponseDTO> getTransactionById(String id);
    BaseClassDomain<List<TransactionResponseDTO>> getTransactionsByDateRange(Date startDate, Date endDate);
}

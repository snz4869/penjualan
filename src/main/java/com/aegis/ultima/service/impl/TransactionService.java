package com.aegis.ultima.service.impl;

import com.aegis.ultima.dto.TransactionDetailRequestDTO;
import com.aegis.ultima.dto.TransactionDetailResponseDTO;
import com.aegis.ultima.dto.TransactionRequestDTO;
import com.aegis.ultima.dto.TransactionResponseDTO;
import com.aegis.ultima.model.*;
import com.aegis.ultima.repository.ProductRepository;
import com.aegis.ultima.repository.TransactionDetailRepository;
import com.aegis.ultima.repository.TransactionRepository;
import com.aegis.ultima.service.ITransactionService;
import com.aegis.ultima.util.BaseClassDomain;
import com.aegis.ultima.util.CommonFunction;
import com.aegis.ultima.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionDetailRepository transactionDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private CommonFunction commonFunction;

    String loginUsername = commonFunction.getLoggedInUsername();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseClassDomain<TransactionRequestDTO> createTransaction(TransactionRequestDTO request) {
        BaseClassDomain<TransactionRequestDTO> returnValue = new BaseClassDomain<TransactionRequestDTO>();
        try{
            Transaction transaction = new Transaction();
            transaction.setId(UUID.randomUUID().toString());
            transaction.setStatus(request.getStatus());
            transaction.setTotal(request.getTotalAmount());
            transaction.setRemarks(request.getRemarks());
            transaction.setCreatedAt(DateUtils.getCurrentDate());
            transaction.setCreatedBy(loginUsername);

            for (TransactionDetailRequestDTO detail : request.getTransactionDetails()) {
                Product product = productRepository.findProductByCode(detail.getProductCode());
                if (product == null) {
                    returnValue.setResponseCode("99");
                    returnValue.setDescErrorCode("Product not found for code: " + detail.getProductCode());
                    return returnValue;
                }

                if (product.getStock() < detail.getQuantity()) {
                    returnValue.setResponseCode("99");
                    returnValue.setDescErrorCode("Insufficient stock for product: " + product.getName());
                    return returnValue;
                }

                if (request.getStatus().equals("COMPLETED")){
                    product.setStock(product.getStock() - detail.getQuantity());
                    productRepository.save(product);
                } else if (request.getStatus().equals("REFUND")){
                    product.setStock(product.getStock() + detail.getQuantity());
                    productRepository.save(product);
                }

                TransactionDetails transactionDetail = new TransactionDetails();
                transactionDetail.setTransactionId(transaction.getId());
                transactionDetail.setProductCode(product.getCode());
                transactionDetail.setQuantity(detail.getQuantity());
                transactionDetail.setPrice(product.getPrice());
                transactionDetail.setCreatedBy(loginUsername);
                transactionDetail.setCreatedAt(DateUtils.getCurrentDate());
                transactionDetailRepository.save(transactionDetail);
            }
            transactionRepository.save(transaction);
            returnValue.setResponseSucceed(request);

        } catch (Exception e){
            returnValue.setResponseException();
            throw e;
        }

        return returnValue;
    }

    @Override
    public BaseClassDomain<TransactionResponseDTO> getTransactionById(String id) {
        BaseClassDomain<TransactionResponseDTO> returnValue = new BaseClassDomain<TransactionResponseDTO>();
        try {
            TransactionResponseDTO transactionResponsetDTO = new TransactionResponseDTO();
            Transaction transaction = new Transaction();
            transaction = transactionRepository.findTransactionById(id);

            if (transaction != null){
                transactionResponsetDTO.setId(transaction.getId());
                transactionResponsetDTO.setStatus(transaction.getStatus());
                transactionResponsetDTO.setTotalAmount(transaction.getTotal());
                transactionResponsetDTO.setRemarks(transaction.getRemarks());
                transactionResponsetDTO.setCreatedAt(transaction.getCreatedAt());
                transactionResponsetDTO.setCreatedBy(transaction.getCreatedBy());
                transactionResponsetDTO.setUpdatedAt(transaction.getUpdatedAt());
                transactionResponsetDTO.setUpdatedBy(transaction.getUpdatedBy());
                transactionResponsetDTO.setDeletedAt(transaction.getDeletedAt());
                transactionResponsetDTO.setDeletedBy(transaction.getDeletedBy());

                List<TransactionDetails> transactionDetails = transactionDetailRepository.findTransactionDetailsByTransactionId(id);
                List<TransactionDetailResponseDTO> trxDetailsDto = new ArrayList<>();

                for (TransactionDetails detail : transactionDetails) {
                    TransactionDetailResponseDTO trxDetails = new TransactionDetailResponseDTO();
                    trxDetails.setId(detail.getId());
                    trxDetails.setTransactionId(detail.getTransactionId());
                    trxDetails.setProductCode(detail.getProductCode());
                    trxDetails.setQuantity(detail.getQuantity());
                    trxDetails.setPrice(detail.getPrice());

                    trxDetailsDto.add(trxDetails);
                }

                transactionResponsetDTO.setTransactionDetails(trxDetailsDto);
                returnValue.setResponseSucceed(transactionResponsetDTO);
            } else {
                returnValue.setResponseCode("99");
                returnValue.setDescErrorCode("Transaction not found");
            }
        } catch (Exception e){
            returnValue.setResponseException();
        }
        return returnValue;
    }

    @Override
    public BaseClassDomain<List<TransactionResponseDTO>> getTransactionsByDateRange(Date startDate, Date endDate) {
        BaseClassDomain<List<TransactionResponseDTO>> returnValue = new BaseClassDomain<List<TransactionResponseDTO>>();
        try{
            List<Transaction> transactions = transactionRepository.findTransactionsByDateRange(startDate, endDate);
            List<TransactionResponseDTO> transactionResponseDTOs = new ArrayList<>();

            if (transactions == null || transactions.isEmpty()) {
                returnValue.setResponseCode("99");
                returnValue.setDescErrorCode("Transaction not found");
                return returnValue;
            }

            for (Transaction transaction : transactions) {
                TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
                transactionResponseDTO.setId(transaction.getId());
                transactionResponseDTO.setStatus(transaction.getStatus());
                transactionResponseDTO.setTotalAmount(transaction.getTotal());
                transactionResponseDTO.setRemarks(transaction.getRemarks());
                transactionResponseDTO.setCreatedAt(transaction.getCreatedAt());
                transactionResponseDTO.setCreatedBy(transaction.getCreatedBy());
                transactionResponseDTO.setUpdatedAt(transaction.getUpdatedAt());
                transactionResponseDTO.setUpdatedBy(transaction.getUpdatedBy());
                transactionResponseDTO.setDeletedAt(transaction.getDeletedAt());
                transactionResponseDTO.setDeletedBy(transaction.getDeletedBy());

                List<TransactionDetails> transactionDetails = transactionDetailRepository.findTransactionDetailsByTransactionId(transaction.getId());
                List<TransactionDetailResponseDTO> trxDetailsDto = new ArrayList<>();

                for (TransactionDetails detail : transactionDetails) {
                    TransactionDetailResponseDTO trxDetails = new TransactionDetailResponseDTO();
                    trxDetails.setId(detail.getId());
                    trxDetails.setTransactionId(detail.getTransactionId());
                    trxDetails.setProductCode(detail.getProductCode());
                    trxDetails.setQuantity(detail.getQuantity());
                    trxDetails.setPrice(detail.getPrice());

                    trxDetailsDto.add(trxDetails);
                }

                transactionResponseDTO.setTransactionDetails(trxDetailsDto);
                transactionResponseDTOs.add(transactionResponseDTO);
            }
            returnValue.setResponseSucceed(transactionResponseDTOs);
        } catch (Exception e){
            returnValue.setResponseException();
        }
        return returnValue;
    }
}

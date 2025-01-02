package com.aegis.ultima.controller;

import com.aegis.ultima.dto.TransactionRequestDTO;
import com.aegis.ultima.dto.TransactionResponseDTO;
import com.aegis.ultima.service.ITransactionService;
import com.aegis.ultima.util.BaseClassDomain;
import com.aegis.ultima.util.CommonFunction;
//import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    ITransactionService transactionService;

    @Autowired
    private CommonFunction commonFunction;

    private final static Logger logger = LogManager.getLogger(ProductController.class);

//    Gson json = new Gson();

    @PostMapping("/add")
    public BaseClassDomain<TransactionRequestDTO> createTransaction(@RequestBody TransactionRequestDTO transactionRequest) {
        logger.info("----Request transactions/add ---->");
        logger.info("add request::" + commonFunction.convertJSONtoString(transactionRequest));

        BaseClassDomain<TransactionRequestDTO> objReturn = new BaseClassDomain<TransactionRequestDTO>();
        objReturn = transactionService.createTransaction(transactionRequest);

        logger.info("----Response product/add----");
        logger.info("add response::" + commonFunction.convertJSONtoString(objReturn));

        return objReturn;
    }

    @GetMapping("/id/{id}")
    public BaseClassDomain<TransactionResponseDTO> getTransactionById(@PathVariable String id) {
        logger.info("----Request transactions/id ---->");
        logger.info("id request::" + commonFunction.convertJSONtoString(id));

        BaseClassDomain<TransactionResponseDTO> objReturn = new BaseClassDomain<TransactionResponseDTO>();
        objReturn = transactionService.getTransactionById(id);

        logger.info("----Response product/register----");
        logger.info("id response::" + commonFunction.convertJSONtoString(objReturn));

        return objReturn;
    }

    @GetMapping("/report")
    public BaseClassDomain<List<TransactionResponseDTO>> getTransactionsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        logger.info("----Request transactions/report ---->");
        logger.info("report request::" + commonFunction.convertJSONtoString(startDate));
        logger.info("report request::" + commonFunction.convertJSONtoString(endDate));

        BaseClassDomain<List<TransactionResponseDTO>> objReturn = new BaseClassDomain<List<TransactionResponseDTO>>();
        objReturn = transactionService.getTransactionsByDateRange(startDate, endDate);

        logger.info("----Response product/report----");
        logger.info("report response::" + commonFunction.convertJSONtoString(objReturn));

        return objReturn;
    }
}

package com.gfg.jdbl_2_transaction_service_major_project;

import com.gfg.jdbl_2_transaction_service_major_project.model.TxDto;

import java.util.List;

public interface TransactionManager {
    String createTransaction(TxDto txDto);
    TxDto getTransaction(String txId) throws Exception;
    List<TxDto> getTransactionHistory(String username);

    void updateTransaction(String status);
}

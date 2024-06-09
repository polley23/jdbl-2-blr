package com.gfg.jdbl_2_wallet_service_major_project;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("transaction-service")
public interface TxClient {
    @GetMapping("/api/transaction")
    ResponseEntity<List<TxDto>> getTransactions(@RequestParam(value = "transaction_id", required = false) String txID,
                                                @RequestParam(value = "username", required = false) String userName);
}

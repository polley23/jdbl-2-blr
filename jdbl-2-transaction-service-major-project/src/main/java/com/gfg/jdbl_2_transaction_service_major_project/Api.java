package com.gfg.jdbl_2_transaction_service_major_project;

import com.gfg.jdbl_2_transaction_service_major_project.model.TxDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class Api {
    @Autowired
    TransactionManager transactionManager;

    @PostMapping("/api/transaction")
    ResponseEntity createTransaction(@RequestBody TxDto txDto){
        String txID = transactionManager.createTransaction(txDto);
        return ResponseEntity.ok(txID);

    }

    @GetMapping("/api/transaction")
    ResponseEntity<List<TxDto>> getTransactions(@RequestParam(value = "transaction_id", required = false, defaultValue = "") String txID,
                                                @RequestParam(value = "username", required = false, defaultValue = "") String userName){
        if(!txID.isEmpty()){
            try {
                return ResponseEntity.ok(Arrays.asList(transactionManager.getTransaction(txID)));
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        } else if(!userName.isEmpty()){
            return  ResponseEntity.ok(transactionManager.getTransactionHistory(userName));
        }
        return  ResponseEntity.badRequest().build();
    }
}

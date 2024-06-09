package com.gfg.jdbl_2_transaction_service_major_project;

import com.gfg.jdbl_2_transaction_service_major_project.Entity.WalletTransaction;
import com.gfg.jdbl_2_transaction_service_major_project.model.Status;
import com.gfg.jdbl_2_transaction_service_major_project.model.TxDto;
import com.gfg.jdbl_2_transaction_service_major_project.model.TxType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionManagerImpl implements TransactionManager{
    @Autowired
    TxRepository txRepository;
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public String createTransaction(TxDto txDto) {
        WalletTransaction walletTransaction = null;
        String txID = UUID.randomUUID().toString();
        if (txDto.getTxType().equals(TxType.TRANSFER)){
             walletTransaction = WalletTransaction.
                    builder()
                    .amount(txDto.getAmount())
                    .fromUser(txDto.getFrom_user())
                    .status(Status.PENDING)
                    .toUser(txDto.getTo_user())
                    .txType(txDto.getTxType())
                     .txId(txID)
                    .build();

        }else{
             walletTransaction = WalletTransaction.
                    builder()
                    .amount(txDto.getAmount())
                    .fromUser(txDto.getFrom_user())
                    .status(Status.PENDING)
                    .txType(txDto.getTxType())
                     .txId(txID)
                    .build();
        }
        txRepository.save(walletTransaction);
        kafkaTemplate.send("transaction-jdbl-2", txID);
        return txID;

    }

    @Override
    public TxDto getTransaction(String txId) throws Exception {
        WalletTransaction walletTransaction = txRepository.findByTxId(txId)
                .orElseThrow(()-> new Exception("transaction not found"));
        return TxDto.builder()
                .amount(walletTransaction.getAmount())
                .status(walletTransaction.getStatus())
                .from_user(walletTransaction.getFromUser())
                .to_user(walletTransaction.getToUser())
                .txType(walletTransaction.getTxType())
                .build();
    }

    @Override
    public List<TxDto> getTransactionHistory(String username) {
        List<WalletTransaction> walletTransactions = txRepository.findAllByFromUser(username);
        return walletTransactions.stream()
                .map(
                        walletTransaction -> TxDto.builder()
                                .amount(walletTransaction.getAmount())
                                .status(walletTransaction.getStatus())
                                .from_user(walletTransaction.getFromUser())
                                .to_user(walletTransaction.getToUser())
                                .txType(walletTransaction.getTxType())
                                .build()
                ).collect(Collectors.toList());

    }

    @Override
    @KafkaListener(topics = "wallet-jdbl-2", groupId = "transaction-app")
    public void updateTransaction(String status) {
        log.info("Status is "+ status);

        boolean isSuccess = status.contains("_SUCCESS");
        String txID = "";
        if(isSuccess) {
            txID = status.replace("_SUCCESS", "");
            log.info("tx success");
        }else{
            txID = status.replace("_FAILED", "");
            log.info("tx failed");

        }
        log.info("txID is "+ txID);
        Optional<WalletTransaction> walletTransactionOpt = txRepository.findByTxId(txID);
        if(walletTransactionOpt.isEmpty()){
            return;
        }

        WalletTransaction walletTransaction = walletTransactionOpt.get();

        if(isSuccess){
            walletTransaction.setStatus(Status.SUCCESSFUL);
        }else{
            walletTransaction.setStatus(Status.FAILED);

        }
        txRepository.save(walletTransaction);
    }
}

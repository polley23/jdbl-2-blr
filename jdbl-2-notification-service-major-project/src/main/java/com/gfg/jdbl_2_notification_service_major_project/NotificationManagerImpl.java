package com.gfg.jdbl_2_notification_service_major_project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationManagerImpl implements NotificationManager {
    @Autowired
    TxClient txClient;

    @Override
    @KafkaListener(topics = "user-jdbl-2", groupId = "notification-app")
    public void notifyOnUserCreation(String email) {
        log.info("A new user has signed up : "+email);
    }

    @Override
    @KafkaListener(topics = "wallet-jdbl-2", groupId = "notification-app")
    public void notifyOnWalletTransaction(String status) {
        boolean isSuccess = status.contains("_SUCCESS");
        String txID = "";
        Status txStatus = Status.PENDING;
        if(isSuccess) {
            txID = status.replace("_SUCCESS", "");
            txStatus = Status.SUCCESSFUL;
            log.info("tx success");
        }else{
            txID = status.replace("_FAILED", "");
            log.info("tx failed");
            txStatus = Status.FAILED;
        }

        TxDto txDto =  txClient.getTransactions(txID,"").getBody().get(0);

        if(txDto.getTxType().equals(TxType.TRANSFER)){
            log.info("Transaction of amount " + txDto.getAmount() +
                    " from "+txDto.from_user+ " to "+ txDto.to_user + " has been "+  txStatus.name());

        }

        else if(txDto.getTxType().equals(TxType.CREDIT)){
            log.info("Credit transaction for user "+txDto.getFrom_user() + " is "+ txStatus.name());

        }
        else if(txDto.getTxType().equals(TxType.DEBIT)){
            log.info("Debit transaction for user "+txDto.getFrom_user() + " is "+ txStatus.name());

        }

    }
}

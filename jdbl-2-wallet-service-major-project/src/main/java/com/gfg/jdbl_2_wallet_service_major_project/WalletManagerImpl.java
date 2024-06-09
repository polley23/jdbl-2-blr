package com.gfg.jdbl_2_wallet_service_major_project;

import com.gfg.jdbl_2_wallet_service_major_project.entity.Wallet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class WalletManagerImpl implements WalletManager{

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    TxClient txClient;

    @Override
    @KafkaListener(topics = "user-jdbl-2", groupId = "wallet-app")
    public void createWallet(String email) {
        Wallet wallet = Wallet.builder()
                .email(email)
                .amount(0d)
                .build();
        walletRepository.save(wallet);
    }

    @Override
    @Transactional
    @KafkaListener(topics = "transaction-jdbl-2", groupId = "wallet-app")
    public void updateWallet(String txID) {
        log.info("tx id "+ txID);
       TxDto txDto =  txClient.getTransactions(txID,"").getBody().get(0);
       log.info(txDto.from_user);
       String fromUser = txDto.getFrom_user();
       Optional<Wallet> walletForFromUserOpt = walletRepository.findByEmail(fromUser);
       if(walletForFromUserOpt.isEmpty()){
           kafkaTemplate.send("wallet-jdbl-2", txID+"_FAILED");
           return;
       }

       Wallet walletForFromUser = walletForFromUserOpt.get();
       Double amount = txDto.getAmount();
       Double currentWalletAmount = walletForFromUser.getAmount();

        switch (txDto.getTxType()){
           case CREDIT -> {
               walletForFromUser.setAmount(currentWalletAmount + amount);
               walletRepository.save(walletForFromUser);
               break;
           }

           case DEBIT -> {
               if(currentWalletAmount - amount<0){
                   kafkaTemplate.send("wallet-jdbl-2", txID+"_FAILED");
                   return;

               }
               walletForFromUser.setAmount(currentWalletAmount - amount);
               walletRepository.save(walletForFromUser);
               break;

           }
           case TRANSFER -> {
               String toUser = txDto.getTo_user();
               Wallet walletForToUser = walletRepository.findByEmail(toUser).get();
               if(currentWalletAmount - amount<0){
                   kafkaTemplate.send("wallet-jdbl-2", txID+"_FAILED");
                   return;

               }
               walletForFromUser.setAmount(currentWalletAmount - amount);
               walletRepository.save(walletForFromUser);
               walletForToUser.setAmount(currentWalletAmount + amount);
               walletRepository.save(walletForToUser);
               break;
           }
       }

        kafkaTemplate.send("wallet-jdbl-2", txID+"_SUCCESS");
    }

    @Override
    public WalletDto getWallet(String email) throws Exception {
        Wallet walletForFromUser = walletRepository.findByEmail(email).orElseThrow(
                ()-> new Exception("Wallet not found")
        );
        return WalletDto.builder()
                .amount(walletForFromUser.getAmount())
                .email(walletForFromUser.getEmail())
                .build();
    }
}

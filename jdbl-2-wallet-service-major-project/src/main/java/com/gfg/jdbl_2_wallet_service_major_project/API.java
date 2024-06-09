package com.gfg.jdbl_2_wallet_service_major_project;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class API {
    @Autowired
    WalletManager walletManager;
    @GetMapping("/api/wallet")
    ResponseEntity getMapping(@RequestBody WalletDto walletDto){
        log.info(walletDto.getEmail());
        try {
            return ResponseEntity.ok(walletManager.getWallet(walletDto.getEmail()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}

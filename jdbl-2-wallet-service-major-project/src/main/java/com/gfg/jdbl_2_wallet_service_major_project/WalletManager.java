package com.gfg.jdbl_2_wallet_service_major_project;

public interface WalletManager {
    void createWallet(String email);
    void updateWallet(String txID);
    WalletDto getWallet(String email) throws Exception;
}

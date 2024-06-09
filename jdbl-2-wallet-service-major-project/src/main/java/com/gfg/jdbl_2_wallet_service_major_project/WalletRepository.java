package com.gfg.jdbl_2_wallet_service_major_project;

import com.gfg.jdbl_2_wallet_service_major_project.entity.Wallet;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {
    Optional<Wallet> findByEmail(String email);
}

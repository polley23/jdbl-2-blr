package com.gfg.jdbl_2_transaction_service_major_project;

import com.gfg.jdbl_2_transaction_service_major_project.Entity.WalletTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TxRepository extends CrudRepository<WalletTransaction,Long> {
    Optional<WalletTransaction> findByTxId(String  txId);
    List<WalletTransaction> findAllByFromUser(String  userName);


}

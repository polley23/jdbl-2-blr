package com.gfg.jdbl_2_transaction_service_major_project.Entity;

import com.gfg.jdbl_2_transaction_service_major_project.model.Status;
import com.gfg.jdbl_2_transaction_service_major_project.model.TxType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String txId;
    String fromUser;
    String toUser;
    TxType txType;
    Status status;
    Double amount;
}

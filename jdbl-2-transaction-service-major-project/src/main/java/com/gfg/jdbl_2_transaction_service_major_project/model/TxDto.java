package com.gfg.jdbl_2_transaction_service_major_project.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TxDto {
    String from_user;
    String to_user;
    Double amount;
    TxType txType;
    Status status;
}




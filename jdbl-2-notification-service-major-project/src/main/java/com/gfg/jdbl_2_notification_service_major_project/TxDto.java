package com.gfg.jdbl_2_notification_service_major_project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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




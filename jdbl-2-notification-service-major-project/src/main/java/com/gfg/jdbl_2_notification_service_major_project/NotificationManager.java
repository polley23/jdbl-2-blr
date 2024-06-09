package com.gfg.jdbl_2_notification_service_major_project;

import org.springframework.stereotype.Service;

public interface NotificationManager {
    void notifyOnUserCreation(String email);

    void notifyOnWalletTransaction(String txStatus);
}

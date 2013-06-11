package com.thales.ntis.subscriber.services;

import com.thales.ntis.subscriber.datex.DeliverNtisModelNotificationDataRequest;
import com.thales.ntis.subscriber.datex.DeliverNtisModelNotificationDataResponse;

public interface NtisModelNotificationDataService {
    public abstract DeliverNtisModelNotificationDataResponse handle(
            DeliverNtisModelNotificationDataRequest request);
}

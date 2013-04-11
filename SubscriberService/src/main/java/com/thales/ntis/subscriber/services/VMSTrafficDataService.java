package com.thales.ntis.subscriber.services;

import com.thales.ntis.subscriber.datex.DeliverVMSTrafficDataRequest;
import com.thales.ntis.subscriber.datex.DeliverVMSTrafficDataResponse;

public interface VMSTrafficDataService {

    public abstract DeliverVMSTrafficDataResponse handle(
            DeliverVMSTrafficDataRequest request);

}
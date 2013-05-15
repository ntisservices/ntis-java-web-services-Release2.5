package com.thales.ntis.subscriber.services;

import com.thales.ntis.subscriber.datex.DeliverSpeedSensorDataRequest;
import com.thales.ntis.subscriber.datex.DeliverSpeedSensorDataResponse;

/**
 * 
 * @author Tom
 * <p>
 * &copy; Copyright 2011 Thales.
 * </p>
 * Created: (15 May 2013 11:57:31)
 */
public interface SensorTrafficDataService {

    DeliverSpeedSensorDataResponse handle(DeliverSpeedSensorDataRequest request);

}

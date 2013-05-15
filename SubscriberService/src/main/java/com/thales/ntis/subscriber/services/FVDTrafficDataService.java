package com.thales.ntis.subscriber.services;

import com.thales.ntis.subscriber.datex.DeliverSpeedFVDDataRequest;
import com.thales.ntis.subscriber.datex.DeliverSpeedFVDDataResponse;

/**
 * 
 * @author Usman Shabbir
 * <p>
 * &copy; Copyright 2011 Thales.
 * </p>
 * Created: (15 May 2013 11:57:01)
 */
public interface FVDTrafficDataService {

    DeliverSpeedFVDDataResponse handle(DeliverSpeedFVDDataRequest request);

}

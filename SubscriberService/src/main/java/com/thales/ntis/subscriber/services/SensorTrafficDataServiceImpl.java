package com.thales.ntis.subscriber.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.thales.ntis.subscriber.datex.D2LogicalModel;
import com.thales.ntis.subscriber.datex.DeliverSpeedSensorDataRequest;
import com.thales.ntis.subscriber.datex.DeliverSpeedSensorDataResponse;
import com.thales.ntis.subscriber.datex.ElaboratedDataPublication;

/**
 * 
 * @author Usman Shabbir
 * <p>
 * &copy; Copyright 2011 Thales.
 * </p>
 * Created: (15 May 2013 11:57:31)
 */
@Service
public class SensorTrafficDataServiceImpl extends AbstractDatexService implements SensorTrafficDataService {

    private static final Logger LOG = LoggerFactory.getLogger(SensorTrafficDataServiceImpl.class);

    @Override
    public DeliverSpeedSensorDataResponse handle(DeliverSpeedSensorDataRequest request) {
        LOG.info("NEW DeliverSpeedSensorDataRequest RECEIVED!");

        D2LogicalModel d2LogicalModel = request.getD2LogicalModel();
        ElaboratedDataPublication elaboratedDataPublication = null;

        // Validate the D2Logical Model
        if (!validate(d2LogicalModel)) {
            throw new RuntimeException(
                    "Incoming request does not appear to be valid!");
        }

        try {
            elaboratedDataPublication = (ElaboratedDataPublication) d2LogicalModel.getPayloadPublication();
            if (elaboratedDataPublication != null) {

                LOG.info("Publication Time is " + elaboratedDataPublication.getPublicationTime());
                LOG.info("Time Default is  " + elaboratedDataPublication.getTimeDefault());
                LOG.info("Total Elaboated Data objects  are " + elaboratedDataPublication.getElaboratedData().size());

            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

        DeliverSpeedSensorDataResponse response = new DeliverSpeedSensorDataResponse();
        response.setStatus("DeliverSpeedSensorDataRequest: Successful Delivery");

        return response;
    }

}

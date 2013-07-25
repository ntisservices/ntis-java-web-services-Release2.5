package com.thales.ntis.subscriber.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.thales.ntis.subscriber.datex.D2LogicalModel;
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
public class SensorTrafficDataServiceImpl implements TrafficDataService {

    private static final Logger LOG = LoggerFactory.getLogger(SensorTrafficDataServiceImpl.class);

    @Override
    public void handle(D2LogicalModel d2LogicalModel) {
        LOG.info("handling SpeedSensor Request !");

        ElaboratedDataPublication elaboratedDataPublication = null;

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
        LOG.info("SpeedSensor Request: Processing Completed Successfuly");
    }

}

/*
	Copyright (C) 2012 Thales Transportation Systems UK
	Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), 
	to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
	and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
	The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER 
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
	IN THE SOFTWARE.
 */

package com.thales.ntis.subscriber.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thales.ntis.subscriber.dao.NetworkModelDao;
import com.thales.ntis.subscriber.datex.BasicData;
import com.thales.ntis.subscriber.datex.D2LogicalModel;
import com.thales.ntis.subscriber.datex.DeliverMIDASTrafficDataRequest;
import com.thales.ntis.subscriber.datex.DeliverMIDASTrafficDataResponse;
import com.thales.ntis.subscriber.datex.MeasuredDataPublication;
import com.thales.ntis.subscriber.datex.MeasuredValue;
import com.thales.ntis.subscriber.datex.SiteMeasurements;
import com.thales.ntis.subscriber.datex.SiteMeasurementsIndexMeasuredValue;
import com.thales.ntis.subscriber.datex.TrafficConcentration;
import com.thales.ntis.subscriber.datex.TrafficFlow;
import com.thales.ntis.subscriber.datex.TrafficHeadway;
import com.thales.ntis.subscriber.datex.TrafficSpeed;
import com.thales.ntis.subscriber.model.TrafficData;

/**
 * This is an example service class implementation.
 * 
 */
@Service
public class MIDASTrafficDataServiceImpl extends AbstractDatexService implements
        MIDASTrafficDataService {

    private static final Logger LOG = LoggerFactory.getLogger(MIDASTrafficDataServiceImpl.class);

    private static final int MAX_SENSOR_READINGS = 7;

    @Autowired
    private NetworkModelDao networkModelDao;

    /**
     * This method extracts the D2LogicalModel from the incoming request and
     * shows how to extract the Individual sensor readings for the MIDAS sites.
     * 
     */
    @Override
    public DeliverMIDASTrafficDataResponse handle(
            DeliverMIDASTrafficDataRequest request) {

        LOG.info("NEW DeliverMIDASTrafficDataRequest RECEIVED!");
        D2LogicalModel d2LogicalModel = request.getD2LogicalModel();
        MeasuredDataPublication measuredDataPublication = null;

        // Validate the D2Logical Model
        if (!validate(d2LogicalModel)) {
            throw new RuntimeException("Incoming request does not appear to be valid!");
        }

        // MeasuredDataPublication class contains the feed description, feed
        // type, site measurements, publication time and
        // other header information.
        try {
            measuredDataPublication = (MeasuredDataPublication) d2LogicalModel
                    .getPayloadPublication();

            if (measuredDataPublication != null
                    && measuredDataPublication.getHeaderInformation() != null) {

                LOG.info("measurementSiteReference ID is "
                        + measuredDataPublication.getSiteMeasurements().get(0)
                                .getMeasurementSiteReference().getId());

                LOG.info("measurementSiteReference time default is "
                        + measuredDataPublication.getSiteMeasurements().get(0)
                                .getMeasurementTimeDefault().toString());

                // Each MIDAS site is encapsulated within a SiteMeasurements
                // object. Cycle through these to get to the sensor readings for
                // a MIDAS site.
                for (SiteMeasurements siteMeasurements : measuredDataPublication
                        .getSiteMeasurements()) {

                    // Each MIDAS site has a GUID; a unique identifier for the
                    // site. This GUID should be used to find comprehensive
                    // details about the MIDAS site (road name, location,
                    // number of lanes, etc) from the Network Model (found at
                    // TIH website)
                    String siteGUID = siteMeasurements.getMeasurementSiteReference().getId();
                    LOG.info("Retrieved traffic data info for MIDAS site " + siteGUID);

                    // Cycle through the MeasuredValues to get the individual
                    // sensor readings for a MIDAS site.
                    for (SiteMeasurementsIndexMeasuredValue measuredValue : siteMeasurements.getMeasuredValue()) {

                        // Each sensor reading has an index. This represents the
                        // lane number the sensor reading is for. On retrieving
                        // the index, you can use
                        // getLaneNumberFromSiteIndex()
                        // to get the lane number.
                        int siteIndex = measuredValue.getIndex();
                        int laneNumber = getLaneNumberFromSiteIndex(siteGUID, siteIndex);
                        LOG.debug("laneNumber is " + laneNumber);

                        // To determine what type the sensor reading is,
                        // cast the basic data value to the appropriate type and
                        // retrieve the value of interest
                        MeasuredValue mv = measuredValue.getMeasuredValue();
                        BasicData basicData = mv.getBasicData();

                        if (basicData instanceof TrafficFlow) {
                            // For a lane, TrafficFlow will appear 4 times i.e.
                            // flow1, flow2 .... flow4. It will appear in
                            // order.

                        } else if (basicData instanceof TrafficSpeed) {
                            // Now you have TrafficSpeed. Cast it appropriately
                            // to retrieve values you are interested in.

                        } else if (basicData instanceof TrafficHeadway) {
                            // Now you have TrafficHeadway. Cast it
                            // appropriately to retrieve values you are
                            // interested in.

                        } else if (basicData instanceof TrafficConcentration) {
                            // Now you have TrafficConcentration. Cast it
                            // appropriately to retrieve values you are
                            // interested in.

                        }

                    }
                }

                // You can convert the site measurements to your model objects
                // and subsequently persist/manipulate your model objects
                @SuppressWarnings("unused")
                List<TrafficData> trafficData = convertToModelObjects(measuredDataPublication
                        .getSiteMeasurements());

            }

        } catch (Exception e) {
            LOG.error("Error while obtaining MeasuredDataPublication");
            LOG.error(e.getMessage());
        }

        DeliverMIDASTrafficDataResponse response = new DeliverMIDASTrafficDataResponse();
        response.setStatus("DeliverMIDASTrafficDataRequest: Successful Delivery");

        return response;
    }

    /*
     * MIDAS sites consist of lanes. The measuredValueIndex represents a sensor
     * reading <Flow, Speed, Headway, Concentration> for a particular lane. This
     * method determines what lane a sensor reading is for.
     * 
     * To correctly work out the lane number, the method needs to determine the
     * starting lane of the MIDAS site. Most MIDAS sites have a starting lane of
     * 1. However, some MIDAS sites have a hard shoulder lane. These sites have
     * a starting lane of 0. The method makes use of the Network model to
     * determine whether a MIDAS has a starting lane of 0 or 1 and then in turn
     * work out what lane the measuredValueIndex is for.
     */
    private int getLaneNumberFromSiteIndex(String siteGUID, int measuredValueIndex) {

        int startingLane = networkModelDao.getStartingLaneForMidasSite(siteGUID);

        if (measuredValueIndex == 0) {
            return startingLane;
        }

        return (measuredValueIndex / MAX_SENSOR_READINGS) + startingLane;
    }
}

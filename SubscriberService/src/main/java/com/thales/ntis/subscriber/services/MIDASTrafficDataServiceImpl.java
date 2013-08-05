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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.thales.ntis.subscriber.datex.BasicData;
import com.thales.ntis.subscriber.datex.D2LogicalModel;
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
public class MIDASTrafficDataServiceImpl implements
        TrafficDataService {

    private static final Logger LOG = LoggerFactory.getLogger(MIDASTrafficDataServiceImpl.class);

    private static final int MAX_SENSOR_READINGS = 7;

    /**
     * This method extracts the D2LogicalModel from the incoming request and
     * shows how to extract the Individual sensor readings for the MIDAS sites.
     * 
     */
    @Override
    public void handle(
            D2LogicalModel d2LogicalModel) {

        LOG.info("handling MIDAS Request !");
        MeasuredDataPublication measuredDataPublication = null;

        // MeasuredDataPublication class contains the feed description, feed
        // type, site measurements, publication time and
        // other header information.
        try {
            measuredDataPublication = (MeasuredDataPublication) d2LogicalModel
                    .getPayloadPublication();

            LOG.info("Got MeasuredDataPublication from request");

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

        LOG.info("MIDAS Request: Processing Completed Successfuly");
    }

    /**
     * The method below demonstrates how to extract SiteMeasurement from the
     * incoming requests and convert it into a list of your own model classes.
     * 
     * @param siteMeasurements
     * @return
     */
    public List<TrafficData> convertToModelObjects(final List<SiteMeasurements> siteMeasurements) {

        LOG.info("Cycling through the list of site measurements");
        LOG.info("Number of site measurements returned: " + siteMeasurements.size());

        List<TrafficData> trafficDataList = new ArrayList<TrafficData>();

        for (SiteMeasurements measurement : siteMeasurements) {
            TrafficData trafficDatum = new TrafficData();

            // This is how you can the Site Reference ID and set it on your
            // domain class.
            trafficDatum.setGuid(measurement.getMeasurementSiteReference()
                    .getId());

            /*
             * You could calculate the lane and set it on your model object. For
             * example trafficDatum.setLaneNumber(0);
             * 
             * Convert the basic data to either TrafficFlow,
             * TrafficConcentration, TrafficSpeed or TrafficHeadway object and
             * extract the values as below.
             * 
             * TrafficSpeed trafficSpeed = (TrafficSpeed) measurement
             * .getMeasuredValue().get(0).getMeasuredValue() .getBasicData();
             */

            trafficDataList.add(trafficDatum);
        }
        return trafficDataList;
    }
}

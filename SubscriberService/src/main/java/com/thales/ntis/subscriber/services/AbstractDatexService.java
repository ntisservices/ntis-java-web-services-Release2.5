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

import com.thales.ntis.subscriber.datex.D2LogicalModel;
import com.thales.ntis.subscriber.datex.SiteMeasurements;
import com.thales.ntis.subscriber.model.TrafficData;

public abstract class AbstractDatexService {

    private static final Logger LOG = LoggerFactory
            .getLogger(AbstractDatexService.class);

    public boolean validate(D2LogicalModel d2LogicalModel) {

        // D2LogicalModel is at the base element of the request so must not be
        // null.
        if (d2LogicalModel != null) {

            if (LOG.isDebugEnabled()) {
                LOG.debug("D2LogicalModel is " + d2LogicalModel);
            }

        } else {
            LOG.error("D2LogicalModel is null! Incoming request does not appear to be valid!");
            return false;
        }

        // Exchange must not be null.
        if (d2LogicalModel.getExchange() != null) {
            LOG.info("Country is "
                    + d2LogicalModel.getExchange().getSupplierIdentification()
                            .getCountry().value());
            LOG.info("National Identifier is "
                    + d2LogicalModel.getExchange().getSupplierIdentification()
                            .getNationalIdentifier());
        } else {
            LOG.error("Exchange is null! Incoming request does not appear to be valid!");
            return false;
        }
        return true;
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

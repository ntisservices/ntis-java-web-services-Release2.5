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
import org.springframework.stereotype.Service;

import com.thales.ntis.subscriber.datex.BasicData;
import com.thales.ntis.subscriber.datex.D2LogicalModel;
import com.thales.ntis.subscriber.datex.DeliverTMUTrafficDataRequest;
import com.thales.ntis.subscriber.datex.DeliverTMUTrafficDataResponse;
import com.thales.ntis.subscriber.datex.MeasuredDataPublication;
import com.thales.ntis.subscriber.datex.MeasuredValue;
import com.thales.ntis.subscriber.datex.SiteMeasurements;
import com.thales.ntis.subscriber.datex.SiteMeasurementsIndexMeasuredValue;
import com.thales.ntis.subscriber.datex.TrafficConcentration;
import com.thales.ntis.subscriber.datex.TrafficFlow;
import com.thales.ntis.subscriber.datex.TrafficHeadway;
import com.thales.ntis.subscriber.datex.TrafficSpeed;
import com.thales.ntis.subscriber.datex.VehicleFlowValue;

@Service
public class TMUTrafficDataServiceImpl extends AbstractDatexService implements
        TMUTrafficDataService {

    private static final Logger LOG = LoggerFactory.getLogger(TMUTrafficDataServiceImpl.class);

    @Override
    public DeliverTMUTrafficDataResponse handle(DeliverTMUTrafficDataRequest request) {
        LOG.info("NEW DeliverTMUTrafficDataRequest RECEIVED!");

        D2LogicalModel d2LogicalModel = request.getD2LogicalModel();

        // Validate the D2Logical Model
        if (!validate(d2LogicalModel)) {
            throw new RuntimeException("Incoming request does not appear to be valid!");
        }
        try {
            if (d2LogicalModel.getPayloadPublication() != null) {
                List<SiteMeasurements> siteMeasurementsList = ((MeasuredDataPublication) d2LogicalModel.getPayloadPublication())
                        .getSiteMeasurements();
                for (SiteMeasurements siteMeasurements : siteMeasurementsList) {
                    List<SiteMeasurementsIndexMeasuredValue> measuredValueList = siteMeasurements.getMeasuredValue();
                    for (SiteMeasurementsIndexMeasuredValue siteMeasurementsIndexMeasuredValue : measuredValueList) {
                        MeasuredValue measuredValue = siteMeasurementsIndexMeasuredValue.getMeasuredValue();
                        BasicData basicData = measuredValue.getBasicData();
                        if (basicData instanceof TrafficFlow) {
                            VehicleFlowValue value = ((TrafficFlow) basicData).getVehicleFlow();
                            LOG.info("Vehicle flow rate : " + value.getVehicleFlowRate().intValue());
                        } else if (basicData instanceof TrafficSpeed) {
                            float speed = ((TrafficSpeed) basicData).getAverageVehicleSpeed().getSpeed();
                            LOG.info("Traffic speed : " + speed);
                        } else if (basicData instanceof TrafficHeadway) {
                            float headWay = ((TrafficHeadway) basicData).getAverageTimeHeadway().getDuration();
                            LOG.info("Traffic Headway : " + headWay);
                        } else if (basicData instanceof TrafficConcentration) {
                            float percentage = ((TrafficConcentration) basicData).getOccupancy().getPercentage();
                            LOG.info("Traffic concentration percentage : " + percentage);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
        }

        DeliverTMUTrafficDataResponse response = new DeliverTMUTrafficDataResponse();
        response.setStatus("DeliverTMUTrafficDataRequest: Successful Delivery");
        return response;
    }

}

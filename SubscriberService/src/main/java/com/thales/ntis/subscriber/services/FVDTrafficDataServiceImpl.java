/*
    Copyright (C) 2011 Thales Transportation Systems UK
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

import com.thales.ntis.subscriber.datex.D2LogicalModel;
import com.thales.ntis.subscriber.datex.ElaboratedData;
import com.thales.ntis.subscriber.datex.ElaboratedDataPublication;
import com.thales.ntis.subscriber.datex.TrafficSpeed;
import com.thales.ntis.subscriber.datex.TravelTimeData;

/**
 * 
 * @author Usman Shabbir
 * <p>
 * &copy; Copyright 2011 Thales.
 * </p>
 * Created: (15 May 2013 11:57:01)
 */
@Service
public class FVDTrafficDataServiceImpl implements TrafficDataService {

    private static final Logger LOG = LoggerFactory.getLogger(FVDTrafficDataServiceImpl.class);

    @Override
    public void handle(D2LogicalModel d2LogicalModel) {

        LOG.info("handling SpeedFVD Request !");

        ElaboratedDataPublication elaboratedDataPublication = null;

        try {
            elaboratedDataPublication = (ElaboratedDataPublication) d2LogicalModel.getPayloadPublication();
            if (elaboratedDataPublication != null) {
                List<ElaboratedData> trafficDataList = getElaboratedDataListFor(elaboratedDataPublication.getElaboratedData(),
                        TrafficSpeed.class);
                List<ElaboratedData> travelTimeDataList = getElaboratedDataListFor(elaboratedDataPublication.getElaboratedData(),
                        TravelTimeData.class);

                LOG.info("Publication Time is " + elaboratedDataPublication.getPublicationTime());
                LOG.info("Time Default is  " + elaboratedDataPublication.getTimeDefault());
                LOG.info("Total Traffic Data objects  are " + trafficDataList.size());
                LOG.info("Total Travel Time Data objects  are " + travelTimeDataList.size());
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        LOG.info("SpeedFVD Request: Processing Completed Successfuly");
    }

    private List<ElaboratedData> getElaboratedDataListFor(List<ElaboratedData> payLoadElaboratedDataList,
            Class<?> clazz) {
        List<ElaboratedData> edList = new ArrayList<ElaboratedData>();
        for (ElaboratedData ed : payLoadElaboratedDataList) {
            if (ed.getBasicData().getClass().equals(clazz)) {
                edList.add(ed);
            }
        }
        return edList;
    }

}

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thales.ntis.subscriber.datex.D2LogicalModel;
import com.thales.ntis.subscriber.model.FeedType;

/**
 * 
 * @author Anirudha Deshpande
 * 
 * I am factory class responsible for creating different TrafficDataService
 * instances depending on given feedtype
 * 
 */
public class TrafficDataServiceFactory {

    private static final Logger LOG = LoggerFactory.getLogger(TrafficDataService.class);

    public static TrafficDataService newInstance(D2LogicalModel request) {

        String feedType = request.getPayloadPublication().getFeedType();

        LOG.info("FeedType is : " + feedType);

        if (feedType.toLowerCase().contains(FeedType.ANPR.lowerCase())) {
            return new ANPRTrafficDataServiceImpl();
        } else if (feedType.toLowerCase().contains(FeedType.MIDAS.lowerCase())) {
            return new MIDASTrafficDataServiceImpl();
        } else if (feedType.toLowerCase().contains(FeedType.TMU.lowerCase())) {
            return new TMUTrafficDataServiceImpl();

        } else if (feedType.toLowerCase().contains(FeedType.FUSED_SENSOR_ONLY.lowerCase())) {
            return new SensorTrafficDataServiceImpl();

        } else if (feedType.toLowerCase().contains(FeedType.FUSED_FVD_AND_SENSOR_PTD.lowerCase())) {
            return new FVDTrafficDataServiceImpl();
        } else if (feedType.toLowerCase().contains(FeedType.VMS.lowerCase())) {
            return new VMSTrafficDataServiceImpl();

        } else if (feedType.toLowerCase().contains(FeedType.NTIS_MODEL_UPDATE_NOTIFICATION.lowerCase())) {
            return new NtisModelNotificationDataServiceImpl();
        }

        return null;
    }

}

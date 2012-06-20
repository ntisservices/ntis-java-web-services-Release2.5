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
import org.springframework.stereotype.Service;

import com.thales.ntis.subscriber.datex.D2LogicalModel;
import com.thales.ntis.subscriber.datex.DeliverANPRTrafficDataRequest;
import com.thales.ntis.subscriber.datex.DeliverANPRTrafficDataResponse;
import com.thales.ntis.subscriber.datex.JourneyTimePublication;

/**
 * This is an example service class implementation.
 * 
 */
@Service
public class ANPRTrafficDataServiceImpl extends AbstractDatexService implements
        ANPRTrafficDataService {

    private static final Logger LOG = LoggerFactory
            .getLogger(ANPRTrafficDataServiceImpl.class);

    @Override
    public DeliverANPRTrafficDataResponse handle(
            DeliverANPRTrafficDataRequest request) {

        LOG.info("NEW DeliverANPRTrafficDataRequest RECEIVED!");

        D2LogicalModel d2LogicalModel = request.getD2LogicalModel();
        JourneyTimePublication journeyTimePublication = null;

        // Validate the D2Logical Model
        if (!validate(d2LogicalModel)) {
            throw new RuntimeException("Incoming request does not appear to be valid!");
        }

        // JourneyTimePublication contains one or more journey-times
        try {
            journeyTimePublication = (JourneyTimePublication) d2LogicalModel
                    .getPayloadPublication();

            if (journeyTimePublication != null
                    && journeyTimePublication.getJourneyTimes()
                            .getJourneyTime().get(0) != null) {
                // You could use the JourneyTimePublication and extract the
                // corresponding fields.
                LOG.info("JourneyTime: Timestamp is "
                        + journeyTimePublication.getJourneyTimes()
                                .getJourneyTime().get(0).getTimeStamp()
                                .toString());
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

        DeliverANPRTrafficDataResponse response = new DeliverANPRTrafficDataResponse();
        response.setStatus("DeliverANPRTrafficDataRequest: Successful Delivery");

        return response;
    }

}

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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thales.ntis.subscriber.datex.D2LogicalModel;
import com.thales.ntis.subscriber.datex.DeliverMIDASTrafficDataRequest;
import com.thales.ntis.subscriber.datex.DeliverMIDASTrafficDataResponse;
import com.thales.ntis.subscriber.datex.MeasuredDataPublication;
import com.thales.ntis.subscriber.model.TrafficData;

/**
 * This is an example service class implementation.
 * 
 * @author dev
 * 
 */

public class MIDASTrafficDataServiceImpl extends AbstractDatexService implements
		MIDASTrafficDataService {

	private static final Logger LOG = LoggerFactory
			.getLogger(MIDASTrafficDataServiceImpl.class);

	@Override
	public DeliverMIDASTrafficDataResponse handle(
			DeliverMIDASTrafficDataRequest request) {

		LOG.info("NEW DeliverMIDASTrafficDataRequest RECEIVED!");
		D2LogicalModel d2LogicalModel = request.getD2LogicalModel();
		MeasuredDataPublication measuredDataPublication = null;

		// Validate the D2Logical Model
		if (!validate(d2LogicalModel)) {
			throw new RuntimeException(
					"Incoming request does not appear to be valid!");
		}

		// MeasuredDataPublication class contains the feed description, feed
		// type, site measurements, publication time and
		// other header information.
		try {
			measuredDataPublication = (MeasuredDataPublication) d2LogicalModel
					.getPayloadPublication();

			if (measuredDataPublication != null
					&& measuredDataPublication.getHeaderInformation() != null) {
				LOG.debug("measurementSiteReference ID is "
						+ measuredDataPublication.getSiteMeasurements().get(0)
								.getMeasurementSiteReference().getId());
				LOG.debug("measurementSiteReference time default is "
						+ measuredDataPublication.getSiteMeasurements().get(0)
								.getMeasurementTimeDefault().toString());

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
}

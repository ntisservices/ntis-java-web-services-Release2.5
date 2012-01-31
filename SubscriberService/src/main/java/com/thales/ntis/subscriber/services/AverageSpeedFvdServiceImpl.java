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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thales.ntis.subscriber.datex.D2LogicalModel;
import com.thales.ntis.subscriber.datex.DeliverAverageSpeedFvdRequest;
import com.thales.ntis.subscriber.datex.DeliverAverageSpeedFvdResponse;
import com.thales.ntis.subscriber.datex.FusedDataPublication;

/**
 * This is an example service class implementation.
 * 
 * @author dev
 * 
 */

public class AverageSpeedFvdServiceImpl extends AbstractDatexService implements
		AverageSpeedFvdService {

	private static final Logger LOG = LoggerFactory
			.getLogger(AverageSpeedFvdServiceImpl.class);

	@Override
	public DeliverAverageSpeedFvdResponse handle(
			DeliverAverageSpeedFvdRequest request) {

		LOG.info("NEW DeliverAverageSpeedFvdRequest RECEIVED!");

		D2LogicalModel d2LogicalModel = request.getD2LogicalModel();
		FusedDataPublication fusedDataPublication = null;

		// Validate the D2Logical Model
		if (!validate(d2LogicalModel)) {
			throw new RuntimeException(
					"Incoming request does not appear to be valid!");
		}

		// FusedDataPublication contains the journeytime, direction, code,
		// region etc.
		try {
			fusedDataPublication = (FusedDataPublication) d2LogicalModel
					.getPayloadPublication();

			// You could use the FusedDataPublication and extract the
			// corresponding fields.
			LOG.debug("createdUtc is "
					+ fusedDataPublication.getFusedData().get(0)
							.getCreatedUtc().toString());
			LOG.debug("Local is "
					+ fusedDataPublication.getFusedData().get(0).getMarkets()
							.get(0).getCreatedLocal().toString());
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}

		DeliverAverageSpeedFvdResponse response = new DeliverAverageSpeedFvdResponse();
		response.setStatus("DeliverAverageSpeedFvdRequest: Successful Delivery");

		return response;
	}

}

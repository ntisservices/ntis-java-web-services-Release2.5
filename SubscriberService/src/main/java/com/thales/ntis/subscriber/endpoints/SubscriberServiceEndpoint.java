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

package com.thales.ntis.subscriber.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

import com.thales.ntis.subscriber.datex.DeliverANPRTrafficDataRequest;
import com.thales.ntis.subscriber.datex.DeliverANPRTrafficDataResponse;
import com.thales.ntis.subscriber.datex.DeliverAverageJourneyTimeRequest;
import com.thales.ntis.subscriber.datex.DeliverAverageJourneyTimeResponse;
import com.thales.ntis.subscriber.datex.DeliverAverageSpeedFusedDataRequest;
import com.thales.ntis.subscriber.datex.DeliverAverageSpeedFusedDataResponse;
import com.thales.ntis.subscriber.datex.DeliverAverageSpeedFvdRequest;
import com.thales.ntis.subscriber.datex.DeliverAverageSpeedFvdResponse;
import com.thales.ntis.subscriber.datex.DeliverMIDASTrafficDataRequest;
import com.thales.ntis.subscriber.datex.DeliverMIDASTrafficDataResponse;
import com.thales.ntis.subscriber.services.ANPRTrafficDataService;
import com.thales.ntis.subscriber.services.AverageJourneyTimeService;
import com.thales.ntis.subscriber.services.AverageSpeedFusedDataService;
import com.thales.ntis.subscriber.services.AverageSpeedFvdService;
import com.thales.ntis.subscriber.services.MIDASTrafficDataService;

/**
 * This is a reference SubscriberServiceEndpoint. Business logic is delegated to
 * separate service classes.
 * 
 * @author dev
 * 
 */

@Endpoint
public class SubscriberServiceEndpoint {

	private ANPRTrafficDataService aNPRTrafficDataService;
	private AverageJourneyTimeService averageJourneyTimeService;
	private AverageSpeedFusedDataService averageSpeedFusedDataService;
	private AverageSpeedFvdService averageSpeedFvdService;
	private MIDASTrafficDataService mIDASTrafficDataService;

	@PayloadRoot(namespace = "http://www.thalesgroup.com/NTIS/SubscriberService", localPart = "DeliverAverageSpeedFusedDataRequest")
	public DeliverAverageSpeedFusedDataResponse handle(
			DeliverAverageSpeedFusedDataRequest request) {

		// Delegate the business logic implementation to a service.
		DeliverAverageSpeedFusedDataResponse response = averageSpeedFusedDataService
				.handle(request);

		return response;
	}

	@PayloadRoot(namespace = "http://www.thalesgroup.com/NTIS/SubscriberService", localPart = "DeliverAverageSpeedFvdRequest")
	public DeliverAverageSpeedFvdResponse handle(
			DeliverAverageSpeedFvdRequest request) {

		// Delegate the business logic implementation to a service.
		DeliverAverageSpeedFvdResponse response = averageSpeedFvdService
				.handle(request);

		return response;
	}

	@PayloadRoot(namespace = "http://www.thalesgroup.com/NTIS/SubscriberService", localPart = "DeliverAverageJourneyTimeRequest")
	public DeliverAverageJourneyTimeResponse handle(
			DeliverAverageJourneyTimeRequest request) {

		// Delegate the business logic implementation to a service.
		DeliverAverageJourneyTimeResponse response = averageJourneyTimeService
				.handle(request);

		return response;
	}

	@PayloadRoot(namespace = "http://www.thalesgroup.com/NTIS/SubscriberService", localPart = "DeliverMIDASTrafficDataRequest")
	public DeliverMIDASTrafficDataResponse handle(
			DeliverMIDASTrafficDataRequest request) {

		// Delegate the business logic implementation to a service.
		DeliverMIDASTrafficDataResponse response = mIDASTrafficDataService
				.handle(request);

		return response;
	}

	@PayloadRoot(namespace = "http://www.thalesgroup.com/NTIS/SubscriberService", localPart = "DeliverANPRTrafficDataRequest")
	public DeliverANPRTrafficDataResponse handle(
			DeliverANPRTrafficDataRequest request) {

		// Delegate the business logic implementation to a service.
		DeliverANPRTrafficDataResponse response = aNPRTrafficDataService
				.handle(request);

		return response;
	}

	// INJECTED by the spring framework

	public void setaNPRTrafficDataService(
			ANPRTrafficDataService aNPRTrafficDataService) {
		this.aNPRTrafficDataService = aNPRTrafficDataService;
	}

	public void setAverageJourneyTimeService(
			AverageJourneyTimeService averageJourneyTimeService) {
		this.averageJourneyTimeService = averageJourneyTimeService;
	}

	public void setAverageSpeedFusedDataService(
			AverageSpeedFusedDataService averageSpeedFusedDataService) {
		this.averageSpeedFusedDataService = averageSpeedFusedDataService;
	}

	public void setAverageSpeedFvdService(
			AverageSpeedFvdService averageSpeedFvdService) {
		this.averageSpeedFvdService = averageSpeedFvdService;
	}

	public void setmIDASTrafficDataService(
			MIDASTrafficDataService mIDASTrafficDataService) {
		this.mIDASTrafficDataService = mIDASTrafficDataService;
	}

}

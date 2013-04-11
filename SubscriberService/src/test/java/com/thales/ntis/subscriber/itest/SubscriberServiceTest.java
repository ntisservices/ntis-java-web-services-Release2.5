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

package com.thales.ntis.subscriber.itest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import com.thales.ntis.subscriber.datex.CountryEnum;
import com.thales.ntis.subscriber.datex.D2LogicalModel;
import com.thales.ntis.subscriber.datex.DeliverANPRTrafficDataRequest;
import com.thales.ntis.subscriber.datex.DeliverANPRTrafficDataResponse;
import com.thales.ntis.subscriber.datex.DeliverAverageSpeedFusedDataRequest;
import com.thales.ntis.subscriber.datex.DeliverAverageSpeedFusedDataResponse;
import com.thales.ntis.subscriber.datex.DeliverAverageSpeedFvdRequest;
import com.thales.ntis.subscriber.datex.DeliverAverageSpeedFvdResponse;
import com.thales.ntis.subscriber.datex.DeliverMIDASTrafficDataRequest;
import com.thales.ntis.subscriber.datex.DeliverMIDASTrafficDataResponse;
import com.thales.ntis.subscriber.datex.DeliverTMUTrafficDataRequest;
import com.thales.ntis.subscriber.datex.DeliverTMUTrafficDataResponse;
import com.thales.ntis.subscriber.datex.DeliverVMSTrafficDataRequest;
import com.thales.ntis.subscriber.datex.DeliverVMSTrafficDataResponse;
import com.thales.ntis.subscriber.datex.Exchange;
import com.thales.ntis.subscriber.datex.InternationalIdentifier;

/**
 * This is an example integration test implementation.
 * 
 * @author Dev
 * 
 */

public class SubscriberServiceTest extends
        AbstractDependencyInjectionSpringContextTests {

    SubscriberServiceClient marshalingClient = null;
    private static final Logger LOG = LoggerFactory.getLogger(SubscriberServiceTest.class);

    @Override
    protected String[] getConfigLocations() {
        return new String[] { "classpath:/com/thales/ntis/subscriber/itest/subscriber-ws-client.xml" };
    }

    public void onSetUp() {
        marshalingClient = (SubscriberServiceClient) applicationContext
                .getBean("marshallingClient");

    }

    public void testAverageSpeedFusedDataResponse() throws Exception {
        assertEquals(
                expectedAverageSpeedFusedDataResponse().getStatus(),
                marshalingClient.invokeService(
                        createAverageSpeedFusedDataRequest()).getStatus());

    }

    public void testAverageSpeedFvdResponse() throws Exception {
        assertEquals(expectedAverageSpeedFvdResponse().getStatus(),
                marshalingClient.invokeService(createAverageSpeedFvdRequest())
                        .getStatus());

    }

    public void testAverageJourneyTimeResponse() throws Exception {
        assertEquals(
                expectedAverageJourneyTimeResponse().getStatus(),
                marshalingClient.invokeService(
                        createAverageJourneyTimeRequest()).getStatus());

    }

    public void testMIDASTrafficDataResponse() throws Exception {
        assertEquals(expectedMIDASTrafficDataResponse().getStatus(),
                marshalingClient.invokeService(createMIDASTrafficDataRequest())
                        .getStatus());

    }

    public void testANPRTrafficDataResponse() throws Exception {
        assertEquals(expectedANPRTrafficDataResponse().getStatus(),
                marshalingClient.invokeService(createANPRTrafficDataRequest())
                        .getStatus());
    }

    public void testVMSTrafficDataResponse() throws Exception {
        assertEquals(expectedVMSTrafficDataResponse().getStatus(), marshalingClient.invokeService(createVMSTrafficDataRequest())
                .getStatus());
    }

    public void testTMUTrafficDataResponse() throws Exception {
        LOG.info("expected TMUTrafficDataResponse().getStatus() " + expectedTMUTrafficDataResponse().getStatus());
        LOG.info("actual TMUTrafficDataResponse().getStatus() "
                + marshalingClient.invokeService(createTMUTrafficDataRequest())
                        .getStatus());
        assertEquals(expectedTMUTrafficDataResponse().getStatus(), marshalingClient.invokeService(createTMUTrafficDataRequest())
                .getStatus());
    }

    private DeliverAverageSpeedFusedDataRequest createAverageSpeedFusedDataRequest() {
        DeliverAverageSpeedFusedDataRequest request = new DeliverAverageSpeedFusedDataRequest();
        request.setD2LogicalModel(getD2LogicalModel());
        return request;
    }

    private DeliverAverageSpeedFusedDataResponse expectedAverageSpeedFusedDataResponse() {
        DeliverAverageSpeedFusedDataResponse response = new DeliverAverageSpeedFusedDataResponse();
        response.setStatus("DeliverAverageSpeedFusedDataRequest: Successful Delivery");
        return response;
    }

    private DeliverAverageSpeedFvdRequest createAverageSpeedFvdRequest() {
        DeliverAverageSpeedFvdRequest request = new DeliverAverageSpeedFvdRequest();
        request.setD2LogicalModel(getD2LogicalModel());
        return request;

    }

    private DeliverAverageSpeedFvdResponse expectedAverageSpeedFvdResponse() {
        DeliverAverageSpeedFvdResponse response = new DeliverAverageSpeedFvdResponse();
        response.setStatus("DeliverAverageSpeedFvdRequest: Successful Delivery");
        return response;
    }

    private DeliverAverageSpeedFusedDataRequest createAverageJourneyTimeRequest() {
        DeliverAverageSpeedFusedDataRequest request = new DeliverAverageSpeedFusedDataRequest();
        request.setD2LogicalModel(getD2LogicalModel());
        return request;
    }

    private DeliverAverageSpeedFusedDataResponse expectedAverageJourneyTimeResponse() {
        DeliverAverageSpeedFusedDataResponse response = new DeliverAverageSpeedFusedDataResponse();
        response.setStatus("DeliverAverageSpeedFusedDataRequest: Successful Delivery");
        return response;
    }

    private DeliverMIDASTrafficDataRequest createMIDASTrafficDataRequest() {
        DeliverMIDASTrafficDataRequest request = new DeliverMIDASTrafficDataRequest();
        request.setD2LogicalModel(getD2LogicalModel());
        return request;
    }

    private DeliverMIDASTrafficDataResponse expectedMIDASTrafficDataResponse() {
        DeliverMIDASTrafficDataResponse response = new DeliverMIDASTrafficDataResponse();
        response.setStatus("DeliverMIDASTrafficDataRequest: Successful Delivery");
        return response;
    }

    private DeliverANPRTrafficDataRequest createANPRTrafficDataRequest() {
        DeliverANPRTrafficDataRequest request = new DeliverANPRTrafficDataRequest();
        request.setD2LogicalModel(getD2LogicalModel());
        return request;
    }

    private DeliverANPRTrafficDataResponse expectedANPRTrafficDataResponse() {
        DeliverANPRTrafficDataResponse response = new DeliverANPRTrafficDataResponse();
        response.setStatus("DeliverANPRTrafficDataRequest: Successful Delivery");
        return response;
    }

    private DeliverVMSTrafficDataResponse expectedVMSTrafficDataResponse() {
        DeliverVMSTrafficDataResponse response = new DeliverVMSTrafficDataResponse();
        response.setStatus("DeliverVMSTrafficDataRequest: Successful Delivery");
        return response;
    }

    private DeliverVMSTrafficDataRequest createVMSTrafficDataRequest() {
        DeliverVMSTrafficDataRequest request = new DeliverVMSTrafficDataRequest();
        request.setD2LogicalModel(getD2LogicalModel());
        return request;
    }

    private DeliverTMUTrafficDataResponse expectedTMUTrafficDataResponse() {
        DeliverTMUTrafficDataResponse response = new DeliverTMUTrafficDataResponse();
        response.setStatus("DeliverTMUTrafficDataRequest: Successful Delivery");
        return response;
    }

    private DeliverTMUTrafficDataRequest createTMUTrafficDataRequest() {
        DeliverTMUTrafficDataRequest request = new DeliverTMUTrafficDataRequest();
        request.setD2LogicalModel(getD2LogicalModel());
        return request;
    }

    private D2LogicalModel getD2LogicalModel() {
        D2LogicalModel d2LogicalModel = new D2LogicalModel();
        d2LogicalModel.setModelBaseVersion("2");
        d2LogicalModel.setExchange(new Exchange());
        d2LogicalModel.getExchange().setSupplierIdentification(
                new InternationalIdentifier());

        CountryEnum gb = CountryEnum.GB;
        d2LogicalModel.getExchange().getSupplierIdentification().setCountry(gb);
        d2LogicalModel.getExchange().getSupplierIdentification()
                .setNationalIdentifier(CountryEnum.GB.name());
        return d2LogicalModel;
    }
}

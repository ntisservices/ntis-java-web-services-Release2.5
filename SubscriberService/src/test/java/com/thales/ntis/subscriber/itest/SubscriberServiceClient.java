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

import com.thales.ntis.subscriber.datex.DeliverANPRTrafficDataRequest;
import com.thales.ntis.subscriber.datex.DeliverANPRTrafficDataResponse;
import com.thales.ntis.subscriber.datex.DeliverMIDASTrafficDataRequest;
import com.thales.ntis.subscriber.datex.DeliverMIDASTrafficDataResponse;
import com.thales.ntis.subscriber.datex.DeliverSpeedFVDDataRequest;
import com.thales.ntis.subscriber.datex.DeliverSpeedFVDDataResponse;
import com.thales.ntis.subscriber.datex.DeliverSpeedSensorDataRequest;
import com.thales.ntis.subscriber.datex.DeliverSpeedSensorDataResponse;
import com.thales.ntis.subscriber.datex.DeliverTMUTrafficDataRequest;
import com.thales.ntis.subscriber.datex.DeliverTMUTrafficDataResponse;
import com.thales.ntis.subscriber.datex.DeliverVMSTrafficDataRequest;
import com.thales.ntis.subscriber.datex.DeliverVMSTrafficDataResponse;

public interface SubscriberServiceClient {

    public DeliverSpeedFVDDataResponse invokeService(DeliverSpeedFVDDataRequest request);

    public DeliverSpeedSensorDataResponse invokeService(DeliverSpeedSensorDataRequest request);

    public DeliverMIDASTrafficDataResponse invokeService(
            DeliverMIDASTrafficDataRequest request);

    public DeliverANPRTrafficDataResponse invokeService(
            DeliverANPRTrafficDataRequest request);

    public DeliverVMSTrafficDataResponse invokeService(
            DeliverVMSTrafficDataRequest request);

    public DeliverTMUTrafficDataResponse invokeService(
            DeliverTMUTrafficDataRequest request);
}

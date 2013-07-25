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
import org.springframework.stereotype.Service;

import com.thales.ntis.subscriber.datex.D2LogicalModel;

/**
 * This is an example service class implementation.
 * 
 */
@Service
public class DatexIIService extends AbstractDatexService {

    private static final Logger LOG = LoggerFactory.getLogger(DatexIIService.class);

    public synchronized void handle(D2LogicalModel request) {
        LOG.info("Validate D2Logical Model - Started");

        if (!validate(request)) {
            LOG.info("D2Logical Model is not valid");
            throw new RuntimeException("Incoming request does not appear to be valid!");
        }

        LOG.info("Validate D2Logical Model - Completed Successfuly");

        TrafficDataServiceFactory.newInstance(request).handle(request);

    }
}

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

public abstract class AbstractDatexService {

    private static final Logger LOG = LoggerFactory
            .getLogger(AbstractDatexService.class);

    public boolean validate(D2LogicalModel request) {

        // D2LogicalModel is at the base element of the request so must not be
        // null.
        if (request != null) {

            if (LOG.isDebugEnabled()) {
                LOG.debug("D2LogicalModel is " + request);
            }

        } else {
            LOG.error("D2LogicalModel is null! Incoming request does not appear to be valid!");
            return false;
        }

        // Exchange must not be null.
        if (request.getExchange() != null) {
            LOG.info("Country is "
                    + request.getExchange().getSupplierIdentification()
                            .getCountry().value());
            LOG.info("National Identifier is "
                    + request.getExchange().getSupplierIdentification()
                            .getNationalIdentifier());
        } else {
            LOG.error("Exchange is null! Incoming request does not appear to be valid!");
            return false;
        }
        return true;
    }

}

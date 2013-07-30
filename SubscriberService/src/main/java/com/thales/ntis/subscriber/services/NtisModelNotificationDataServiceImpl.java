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

import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.thales.ntis.subscriber.datex.D2LogicalModel;
import com.thales.ntis.subscriber.datex.GenericPublication;
import com.thales.ntis.subscriber.datex.GenericPublicationExtensionType;
import com.thales.ntis.subscriber.datex.NtisModelVersionInformation;

/**
 * 
 * 
 * @author ntis
 * <p>
 * &copy; Copyright 2011 Thales.
 * </p>
 * Created: (30 Jul 2013 09:25:38)
 */
@Service
public class NtisModelNotificationDataServiceImpl implements TrafficDataService {

    private static final Logger LOG = LoggerFactory.getLogger(NtisModelNotificationDataServiceImpl.class);

    @Override
    public void handle(D2LogicalModel d2LogicalModel) {
        LOG.info("handling Ntis Model Notification Request !");

        if (d2LogicalModel.getPayloadPublication() != null) {
            GenericPublication genericPublication = ((GenericPublication) d2LogicalModel.getPayloadPublication());
            XMLGregorianCalendar publicationTime = genericPublication.getPublicationTime();
            LOG.info("Ntis model publication time: " + publicationTime);
            String genericPublicationName = genericPublication.getGenericPublicationName();
            LOG.info("Generic publication name: " + genericPublicationName);
            GenericPublicationExtensionType genericPublicationExtension = genericPublication.getGenericPublicationExtension();
            NtisModelVersionInformation ntisModelVersionInformation = genericPublicationExtension
                    .getNtisModelVersionInformation();
            LOG.info("Network model - file name " + ntisModelVersionInformation.getModelFilename());
            LOG.info("Network model - version " + ntisModelVersionInformation.getModelVersion());
            LOG.info("Network model - publication time " + ntisModelVersionInformation.getModelPublicationTime());
        }

        LOG.info("Ntis Model Notification Data Request: Processing Completed Successfuly");
    }
}

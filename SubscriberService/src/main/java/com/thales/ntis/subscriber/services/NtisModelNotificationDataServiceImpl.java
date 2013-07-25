package com.thales.ntis.subscriber.services;

import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.thales.ntis.subscriber.datex.D2LogicalModel;
import com.thales.ntis.subscriber.datex.GenericPublication;
import com.thales.ntis.subscriber.datex.GenericPublicationExtensionType;
import com.thales.ntis.subscriber.datex.NtisModelVersionInformation;

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
            NtisModelVersionInformation ntisModelVersionInformation = genericPublicationExtension.getNtisModelVersionInformation();
            LOG.info("Network model - file name " + ntisModelVersionInformation.getModelFilename());
            LOG.info("Network model - version " + ntisModelVersionInformation.getModelVersion());
            LOG.info("Network model - publication time " + ntisModelVersionInformation.getModelPublicationTime());
        }

        LOG.info("Ntis Model Notification Data Request: Processing Completed Successfuly");
    }
}

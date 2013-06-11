package com.thales.ntis.subscriber.services;

import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.thales.ntis.subscriber.datex.D2LogicalModel;
import com.thales.ntis.subscriber.datex.DeliverNtisModelNotificationDataRequest;
import com.thales.ntis.subscriber.datex.DeliverNtisModelNotificationDataResponse;
import com.thales.ntis.subscriber.datex.ExtensionType;
import com.thales.ntis.subscriber.datex.GenericPublication;
@Service
public class NtisModelNotificationDataServiceImpl extends AbstractDatexService implements NtisModelNotificationDataService {

    private static final Logger LOG = LoggerFactory.getLogger(NtisModelNotificationDataServiceImpl.class);

    @Override
    public DeliverNtisModelNotificationDataResponse handle(DeliverNtisModelNotificationDataRequest request) {
        LOG.info("NEW DeliverNtisModelNotificationDataRequest RECEIVED!");
        D2LogicalModel d2LogicalModel = request.getD2LogicalModel();
        if (!validate(d2LogicalModel)) {
            throw new RuntimeException("Incoming request does not appear to be valid!");
        }

        if (d2LogicalModel.getPayloadPublication() != null) {
            GenericPublication genericPublication = ((GenericPublication) d2LogicalModel.getPayloadPublication());
            XMLGregorianCalendar publicationTime = genericPublication.getPublicationTime();
            LOG.info("Ntis model publication time: " + publicationTime);
            String genericPublicationName = genericPublication.getGenericPublicationName();
            LOG.info("Generic publication name: " + genericPublicationName);
            ExtensionType genericPublicationExtension = genericPublication.getGenericPublicationExtension();
            LOG.info("Generic Publication Extension: " + genericPublicationExtension);
        }
        DeliverNtisModelNotificationDataResponse response = new DeliverNtisModelNotificationDataResponse();
        response.setStatus("DeliverNtisModelNotificationDataRequest: Successful Delivery");
        return response;
    }
}

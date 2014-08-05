package com.thales.ntis.subscriber.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.thales.ntis.subscriber.datex.D2LogicalModel;
import com.thales.ntis.subscriber.datex.MaintenanceWorks;
import com.thales.ntis.subscriber.datex.RoadMaintenanceTypeEnum;
import com.thales.ntis.subscriber.datex.Situation;
import com.thales.ntis.subscriber.datex.SituationPublication;
import com.thales.ntis.subscriber.datex.SituationRecord;

/**
 * This is an example service class implementation.
 * 
 */
@Service
public class EventDataServiceImpl implements
        TrafficDataService {

    private static final Logger LOG = LoggerFactory.getLogger(EventDataServiceImpl.class);
    private static final String PUBLICATION_TYPE = "Event Data Publication";

    @Override
    public void handle(D2LogicalModel d2LogicalModel) {

        LOG.info(PUBLICATION_TYPE + ": received...");

        SituationPublication situationPublication = null;

        try {
            situationPublication = (SituationPublication) d2LogicalModel.getPayloadPublication();
            if (situationPublication != null) {
                List<Situation> situations = situationPublication.getSituation();

                LOG.info("Number of Events in payload: " + situations.size());

                for (Situation situation : situations) {
                    // Only have 1 situationRecord per situation (index=0)
                    SituationRecord situationRecord = situation.getSituationRecord().get(0);

                    // Different types of event/situation record contain some common information and 
                    // some type-specific data items and should be handled accordingly
                    processCommonEventData(situationRecord);

                    if (MaintenanceWorks.class.equals(situationRecord.getClass())) {
                        processMaintenanceWorksEvent((MaintenanceWorks) situationRecord);
                    }

                }
                LOG.info(PUBLICATION_TYPE + ": processed successfully.");
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    private void processCommonEventData(SituationRecord situationRecord) {
        LOG.info("Event ID: " + situationRecord.getId());
        LOG.info("Severity: " + situationRecord.getSeverity());
        LOG.info("Current status: " + situationRecord.getValidity().getValidityStatus());
        LOG.info("Overall start time: " + situationRecord.getValidity().getValidityTimeSpecification().getOverallStartTime());
        LOG.info("Overall end time: " + situationRecord.getValidity().getValidityTimeSpecification().getOverallEndTime());
    }

    private void processMaintenanceWorksEvent(MaintenanceWorks maintenanceWorksEvent) {
        if (maintenanceWorksEvent.isUrgentRoadworks())
            LOG.info("Urgent Roadworks!");
        List<RoadMaintenanceTypeEnum> maintenanceTypes = maintenanceWorksEvent.getRoadMaintenanceType();
        for (RoadMaintenanceTypeEnum maintenanceType : maintenanceTypes) {
            LOG.info("Type of maintenance involved: " + maintenanceType);
        }
        LOG.info("Mobility: " + maintenanceWorksEvent.getMobility().getMobilityType());
        LOG.info("Scale: " + maintenanceWorksEvent.getRoadworksScale());
        LOG.info("Roadworks Scheme Name: "
                + maintenanceWorksEvent.getMaintenanceWorksExtension().getRoadworksEventDetails().getRoadworksSchemeName());
    }
}
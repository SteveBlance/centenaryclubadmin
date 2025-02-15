package com.codaconsultancy.centenaryclubadmin.service;

import com.codaconsultancy.centenaryclubadmin.domain.Configuration;
import com.codaconsultancy.centenaryclubadmin.domain.EventLog;
import com.codaconsultancy.centenaryclubadmin.repositories.ConfigurationRepository;
import com.codaconsultancy.centenaryclubadmin.repositories.EventLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LifelineService {

    public static final String LAST_ELIGIBILITY_REFRESH_DATE = "LAST_ELIGIBILITY_REFRESH_DATE";
    public static final String ELIGIBILITY_REFRESH_REQUIRED = "ELIGIBILITY_REFRESH_REQUIRED";

    private EventLogRepository eventLogRepository;

    ConfigurationRepository configurationRepository;

    @Autowired
    public void setEventLogRepository(EventLogRepository eventLogRepository) {
        this.eventLogRepository = eventLogRepository;
    }

    @Autowired
    public void setConfigurationRepository(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public void logMessage(String message) {
        eventLogRepository.save(new EventLog(message));
    }

    public void forceDrawEligibilityRefresh() {
        Configuration refreshRequired = configurationRepository.findByName(ELIGIBILITY_REFRESH_REQUIRED);
        if (!refreshRequired.getBooleanValue()) {
            refreshRequired.setBooleanValue(true);
            configurationRepository.save(refreshRequired);
        }
    }
}

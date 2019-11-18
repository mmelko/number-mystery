package org.mmelko.test.syndesis.stats;

import org.mmelko.test.syndesis.stats.model.Activity;
import org.mmelko.test.syndesis.stats.model.ActivityList;
import org.mmelko.test.syndesis.stats.model.IntegrationOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/health")
public class HealthAndStats {

@Autowired
SyndesisRestWrapper restWrapper;

    @RequestMapping("/integrations")
    public List<IntegrationOverview> getIntegrations() {
        return restWrapper.getIntegrationOverviews();
    }

    @RequestMapping("/activities/{id}")
    public List<Activity> getActivities(@PathVariable String id) {
        return restWrapper.getActivities(id);
    }

    @RequestMapping("/activities")
    public List<ActivityList> getAllActivities() {
        return restWrapper.getAllActivities();
    }

    @RequestMapping("status")
    public String getStatus() {
        return "OK";

    }
}

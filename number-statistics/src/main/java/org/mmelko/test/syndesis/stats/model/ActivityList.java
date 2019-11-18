package org.mmelko.test.syndesis.stats.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ActivityList {
    private List<Activity> activityList;
    private String integrationId;
    private String integrationName;
}

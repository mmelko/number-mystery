package org.mmelko.test.syndesis.stats.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntegrationOverview {
    private String id;
    private String name;
    private String currentState;
    private String targetState;


}

package org.mmelko.test.syndesis.stats.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class IntegrationList {
    @JsonProperty("items")
    @Getter
    @Setter
    private List<IntegrationShort> integrationList;

    @Getter
    @Setter
    @JsonProperty("count")
    private int count;

}

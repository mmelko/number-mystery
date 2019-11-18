package org.mmelko.test.syndesis.stats.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Activity {

    private String id;
    private String logts;
    private Long at;
    private String pod;
  //  private String ver;
    private String status;
    private Boolean failed;
 //   private JsonNode metadata;
}

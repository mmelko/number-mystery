package org.mmelko.test.syndesis.stats;

import io.syndesis.common.model.integration.Integration;

import org.mmelko.test.syndesis.stats.model.Activity;
import org.mmelko.test.syndesis.stats.model.ActivityList;
import org.mmelko.test.syndesis.stats.model.IntegrationList;
import org.mmelko.test.syndesis.stats.model.IntegrationOverview;
import org.mmelko.test.syndesis.stats.model.IntegrationShort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public  class SyndesisRestWrapper {
    public static final String INTEGRATIONS_ENDPOINT = "http://syndesis-server.syndesis.svc/api/v1/integrations?per_page=50";
    //public static final String INTEGRATIONS_ENDPOINT = "http://localhost:8080/api/v1/integrations?per_page=50";
    public static final String ACTIVITIES_ENDPOINT = "http://syndesis-server.syndesis.svc/api/v1/activity/integrations/";
    //public static final String ACTIVITIES_ENDPOINT = "http://localhost:8080/api/v1/activity/integrations/";
    public static final String OVERVIEW_ENDPOINT = "http://syndesis-server.syndesis.svc/api/v1/integrations/%s/overview/";
    //public static final String OVERVIEW_ENDPOINT = "http://localhost:8080/api/v1/integrations/%s/overview/";

    private HttpHeaders headers;
    private RestTemplate template;

    public SyndesisRestWrapper() {
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON}));
        // Request to return JSON formiler automatically translateat
        // headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Forwarded-User", "pista");
        headers.set("X-Forwarded-Access-Token", "kral");
        headers.set("SYNDESIS-XSRF-TOKEN", "awesome");

        template = new RestTemplate();
    }

    public IntegrationList getIntegrations() {
        HttpEntity<IntegrationList> entity = new HttpEntity<IntegrationList>(headers);
        ResponseEntity<IntegrationList> response = template.exchange(INTEGRATIONS_ENDPOINT, HttpMethod.GET, entity, IntegrationList.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public List<IntegrationOverview> getIntegrationOverviews() {
        List<IntegrationShort> integrations = this.getIntegrations().getIntegrationList();

        return integrations.stream()
            .map(integration -> integration.getId())
            .map(this::getIntegrationOverview)
            .collect(Collectors.toList());
    }

    private IntegrationOverview getIntegrationOverview(String id) {
        HttpEntity<IntegrationOverview> entity = new HttpEntity<IntegrationOverview>(headers);

        // System.out.println()
        ResponseEntity<IntegrationOverview> response =
            template.exchange(String.format(OVERVIEW_ENDPOINT, id), HttpMethod.GET, entity, IntegrationOverview.class);
        String res = "fail";
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public  List<Activity> getActivities(String integrationID) {
        HttpEntity<Activity[]> entity = new HttpEntity<Activity[]>(headers);
        ResponseEntity<Activity[]> response = template.exchange(ACTIVITIES_ENDPOINT + "/" + integrationID, HttpMethod.GET, entity, Activity[].class);
        String res = "fail";

        if (response.getStatusCode() == HttpStatus.OK) {
            return Arrays.asList(response.getBody());
        } else {
            return null;
        }
    }

    public List<ActivityList> getAllActivities() {

        IntegrationList il = this.getIntegrations();
        if (il == null) {
            return null;
        }
        List<IntegrationShort> integrations = il.getIntegrationList();

     return  integrations.stream()
            .map(this::createActivityList)
            .collect(Collectors.toList());
    }



//    private static <T> ResponseEntity<T> getHttpResponse(String url ) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(new MediaType[] {MediaType.APPLICATION_JSON}));
//        // Request to return JSON formiler automatically translateat
//        // headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("X-Forwarded-User", "pista");
//        headers.set("X-Forwarded-Access-Token", "kral");
//        headers.set("SYNDESIS-XSRF-TOKEN", "awesome");
//
//
//        HttpEntity<T> entity = new HttpEntity<T>(headers);
//        RestTemplate  template = new RestTemplate();
//      //  T object=null;
//        ResponseEntity<T>  response = template.exchange(url, HttpMethod.GET, entity,Class<Class<T>>);
//
//        if (response.getStatusCode() == HttpStatus.OK) {
//            return response;
//        } else {
//            throw new HttpClientErrorException("error during request ");
//        }
//    }
    private  ActivityList createActivityList(IntegrationShort integration) {
        ActivityList activityList = new ActivityList();
        activityList.setIntegrationId(integration.getId());
        activityList.setIntegrationName(integration.getName());
        List<Activity> al = this.getActivities(integration.getId());
        activityList.setActivityList(al);
        return activityList;
    }

    private static Object checkResponse(ResponseEntity<?> response) throws HttpClientErrorException {
        if (response.getStatusCode() == HttpStatus.OK) {
            return response;
        } else {
            log.error(response.getStatusCode().getReasonPhrase());
            return null;
        }
    }
}

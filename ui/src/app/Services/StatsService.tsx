import { Statistics } from '../Model/Statistics';
import { Integration } from '../Model/Integration';
import { Activities } from '../Model/Activities';

function formatStats(data: any): Statistics {
    return {
        secretNumber: data.secretNumber,
        attempts: data.attempts,
        guessed: data.guessed,
        lastOne: data.lastOne
    }
}
function formatIntegration(data: any): Integration {
    return {
        id: data.id,
        name: data.name,
        currentState: data.currentState,
        targetState: data.targetState
    };
}

function formatActivities(data: any): Activities {
    var date = new Date(data.activityList[0].at);
    return {
        integrationName : data.integrationName,
        activitiesCount : data.activityList.length,
        lastOne: date
    };
}

function getStats(): Promise<Statistics> {
    return fetch(`http://number-creator-number-mystery.b9ad.pro-us-east-1.openshiftapps.com/`)
        .then(res => res.json())
        .then(stats => formatStats(stats));
}

function getIntegrations(): Promise<Integration[]> {
    return fetch("http://localhost:8081/health/integrations")
        .then(res => res.json())
        .then(res => res.map(formatIntegration))
}
function getActivities(): Promise<Activities[]> {
    return fetch("http://localhost:8081/health/activities")
        .then(res => res.json())
        .then(res => res.map(formatActivities))
}
export { getStats, getIntegrations,getActivities  };
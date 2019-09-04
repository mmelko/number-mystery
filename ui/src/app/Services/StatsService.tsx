import { Statistics } from '../Model/Statistics';

function formatStats(data: any): Statistics {
    return {
        secretNumber: data.secretNumber,
        attempts: data.attempts,
        guessed: data.guessed,
        lastOne: data.lastOne
    }
}

function getStats():Promise<Statistics> { 
   return fetch(`https://bypasscors.herokuapp.com/api/?url=http://number-creator-number-mystery.b9ad.pro-us-east-1.openshiftapps.com/`)
    .then(res => res.json())
    .then(stats => formatStats(stats));
}

export {getStats};
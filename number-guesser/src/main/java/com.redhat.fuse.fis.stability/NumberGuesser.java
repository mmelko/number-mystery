package com.redhat.fuse.fis.stability;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberGuesser {
    private static final Logger LOG = LoggerFactory.getLogger(NumberGuesser.class);
    private static int min = 1;
    private static int max = 100001;
    private static int guessedNumber = 0;

    @RequestMapping(value = "/", produces = "application/json")
    public String status() {
        return String.format("{\"min\":%d," +
            "\"max\":%d," +
            "\"min\":%d}", min, max, guessedNumber);
    }

    @RequestMapping("/reset")
    public String reset() {
        min = 0;
        max = 100001;
        guessedNumber = 0;
        return "reset";
    }

    @RequestMapping("/guess")
    public String guessNumber() {
        guessedNumber = ThreadLocalRandom.current().nextInt(min, max);
        logStatus();
        return guessedNumber + "";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "text/plain")
    public String update(@RequestBody String message) {
        String res = "";
        LOG.info("message" + message);
        if ("bigger".equals(message) && (guessedNumber < max)) {
            min = guessedNumber;
            res = "minimum set";

        } else if ("smaller".equals(message) && (guessedNumber > min)) {
            max = guessedNumber;
            res = "maximum set";
        } else {
            res = "guessed";
            //	reset();
        }
        logStatus();
        return res;
    }

    public int getGuessedNumber() {
        return guessedNumber;
    }

    public void logStatus() {
        LOG.info("Current MIN" + min + " curent MAX " + max + " current guessed:" + guessedNumber);
    }
}

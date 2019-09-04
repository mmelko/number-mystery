package com.redhat.fuse.fis.stability;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;


public class NumberCreator {
    private static SecretNumber secretNumber = new SecretNumber();
    private static final Logger LOG = LoggerFactory.getLogger(NumberCreator.class);

    public String createNew() {
        secretNumber.setSecretNumber(ThreadLocalRandom.current().nextInt(1, 100001));
        return secretNumber.getSecretNumber() + " is secretNumber";
    }

    public String compare(int guess) {
        LOG.info("secret number: " + secretNumber);
        if (secretNumber.getSecretNumber() > guess) {
            secretNumber.guess();
            return "bigger";
        } else if (secretNumber.getSecretNumber() < guess) {
            secretNumber.guess();
            return "smaller";
        } else {
            final int old = secretNumber.getSecretNumber();
            secretNumber.addguessed();
            LOG.info("Creating new number ...");
            createNew();
            return "number:" + old;
        }
    }

    public SecretNumber status() {
        return secretNumber;
    }

}

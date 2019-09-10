package com.redhat.fuse.fis.stability;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ThreadLocalRandom;


public class NumberCreator implements NumberCreatorController {

    @Autowired
    private SecretNumber secretNumber;

    private static final Logger LOG = LoggerFactory.getLogger(NumberCreator.class);

    @Override
    public String createNew() {
        secretNumber.setSecretNumber(ThreadLocalRandom.current().nextInt(1, 100001));
        return secretNumber.getSecretNumber() + " is secretNumber";
    }

    @Override
    public String compare(String number) {
        LOG.info("secret number: " + secretNumber);
        final int guess = Integer.parseInt(number);

        if (secretNumber.getSecretNumber() > guess) {
            secretNumber.guess();
            return "bigger";

        } else if (secretNumber.getSecretNumber() < guess) {
            secretNumber.guess();
            return "smaller";

        } else {
            final int old = secretNumber.getSecretNumber();
            secretNumber.addguessed();
            secretNumber.setLastOne(old);
            LOG.info("Creating new number ...");
            createNew();
            return "number:" + old;
        }
    }

    @Override
    public SecretNumber status() {
        return secretNumber;
    }

    @Override
    public SecretNumber reset() {
        secretNumber.resetAll();
        return secretNumber;
    }

    public void resetAll() {
        secretNumber.resetAll();
    }

}

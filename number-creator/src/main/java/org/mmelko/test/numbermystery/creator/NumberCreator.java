package org.mmelko.test.numbermystery.creator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/")
public class NumberCreator{

    @Autowired
    private SecretNumber secretNumber;

    private static final Logger LOG = LoggerFactory.getLogger(NumberCreator.class);

    @GetMapping("/create")
    public String createNew() {
        secretNumber.setSecretNumber(ThreadLocalRandom.current().nextInt(1, 100001));
        return secretNumber.getSecretNumber() + " is secretNumber";
    }

    @PostMapping("/compare")
    public String compare(@RequestBody String number) {
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

    @GetMapping("/")
    public SecretNumber status() {
        return secretNumber;
    }

    @GetMapping("/reset")
    public SecretNumber reset() {
        secretNumber.resetAll();
        return secretNumber;
    }

    public void resetAll() {
        secretNumber.resetAll();
    }

}

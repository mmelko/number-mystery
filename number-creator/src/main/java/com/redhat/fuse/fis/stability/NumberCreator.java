package com.redhat.fuse.fis.stability;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

import io.swagger.annotations.Api;

@Api("/")
public class NumberCreator implements NumberCreationService {
	private static int secretNumber = 0;
	private static final Logger LOG = LoggerFactory.getLogger(NumberCreator.class);

	public String createNew() {
		secretNumber = ThreadLocalRandom.current().nextInt(1, 100001);
		return secretNumber+" is secretNumber";
	}

	public String compare(int guess) {
		LOG.info("secret number: " + secretNumber);
		if (secretNumber > guess) {
			return "bigger";
		} else if (secretNumber < guess) {
			return "smaller";
		} else {
			final int old = secretNumber;
			LOG.info("Creating new number ...");
			createNew();
			return "number:" + old;
		}
	}

}

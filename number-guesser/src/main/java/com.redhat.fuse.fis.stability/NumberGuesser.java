package com.redhat.fuse.fis.stability;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.concurrent.ThreadLocalRandom;

import io.swagger.annotations.Api;

@Api("/")
public class NumberGuesser implements GuessNumberService {
	private static final Logger LOG = LoggerFactory.getLogger(NumberGuesser.class);
	private static int min = 1;
	private static int max = 100001;
	private static int guessedNumber = 0;

	@GET
	@Path("/reset")
	@Produces(MediaType.TEXT_PLAIN)
	public String reset() {
		min = 0;
		max = 100001;
		guessedNumber = 0;
		return "reset";
	}

	@GET
	@Path("/guess")
	@Produces(MediaType.TEXT_PLAIN)
	public String guessNumber() {
		guessedNumber = ThreadLocalRandom.current().nextInt(min, max);
		logStatus();
		return guessedNumber + "";
	}

	@POST
	@Path("/update")
	@Produces(MediaType.TEXT_PLAIN)
	public String update(String message) {
		String res="";
		LOG.info("message"+message);
		if("bigger".equals(message) && (guessedNumber < max)) {
				min = guessedNumber;
				res = "minimum set";

		}
		else if ("smaller".equals(message) && (guessedNumber > min)) {
			max = guessedNumber;
			res = "maximum set";
		}
		else {
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

package com.redhat.fuse.fis.stability;

import org.springframework.stereotype.Service;

import javax.ws.rs.Path;

@Path("/")
@Service
public interface GuessNumberService {

	String guessNumber();
	String reset();
	String update(String message);


}

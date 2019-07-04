package com.redhat.fuse.fis.stability;

import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Service
public interface NumberCreationService {

	@GET
	@Path("/create")
	@Produces(MediaType.TEXT_PLAIN)
	String createNew();

	@POST
	@Path("/compare")
	@Produces(MediaType.TEXT_PLAIN)
	String compare(int guess);
}

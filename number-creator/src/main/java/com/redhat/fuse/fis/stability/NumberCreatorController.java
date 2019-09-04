package com.redhat.fuse.fis.stability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCreatorController {

    @Autowired
    private NumberCreator numberCreator;

    @RequestMapping("/create")
    String createNew() {
        return numberCreator.createNew();
    }

    //   @POST
    @RequestMapping(method = RequestMethod.POST, path = "/compare", consumes = "text/plain")
    String compare(@RequestBody String guess) {
        return numberCreator.compare(Integer.parseInt(guess));
    }

    //   @GET
    @RequestMapping("/api")
    SecretNumber status() {
        return numberCreator.status();
    }
}

package org.mmelko.test.numbermystery.creator;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface NumberCreatorController {

    @RequestMapping("/create")
    String createNew();

    @RequestMapping(method = RequestMethod.POST, path = "/compare", consumes = "text/plain")
    String compare(@RequestBody String guess);

    @RequestMapping("/")
    SecretNumber status();

    @RequestMapping("/reset")
    SecretNumber reset();
}

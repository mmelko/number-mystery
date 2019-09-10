package com.redhat.fuse.fis.stability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CreatorApplication {

    @Bean
    public NumberCreatorController creator() {
        return new NumberCreator();
    }

    @Bean
    public SecretNumber getSecretNumber() {
        return new SecretNumber();
    }

    public static void main(String[] args) {
        SpringApplication.run(CreatorApplication.class, args);
    }

}

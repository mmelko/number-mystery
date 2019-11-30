package org.mmelko.test.numbermystery.creator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CreatorApplication {

    @Bean
    public SecretNumber getSecretNumber() {
        return new SecretNumber();
    }

    public static void main(String[] args) {
        SpringApplication.run(CreatorApplication.class, args);
    }

}

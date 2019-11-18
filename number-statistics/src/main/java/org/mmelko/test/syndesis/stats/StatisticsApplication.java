package org.mmelko.test.syndesis.stats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StatisticsApplication {

    @Bean
    public SyndesisRestWrapper initSyndesisWrapper() {
        return new SyndesisRestWrapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class, args);
    }
}

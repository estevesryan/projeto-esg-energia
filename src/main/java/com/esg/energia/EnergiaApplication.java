package com.esg.energia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EnergiaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EnergiaApplication.class, args);
    }
}

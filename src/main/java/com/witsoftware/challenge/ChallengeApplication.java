package com.witsoftware.challenge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ChallengeApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChallengeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ChallengeApplication.class, args);
        LOGGER.info("Aplicação iniciada com sucesso!");
    }

}

package com.crypto.config;

import com.crypto.type.EnvironmentType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by Semih, 24.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Component
@Slf4j
@AllArgsConstructor
public class StartupApplicationListener implements CommandLineRunner {

    private final Environment environment;

    // TODO Her run yaptığında bu çalışacak
    @Override
    public void run(String... args) throws Exception {
        if (environment.getActiveProfiles()[0].equals(EnvironmentType.dev.toString())) {
            log.info("Auto Run Start");
        }
    }
}

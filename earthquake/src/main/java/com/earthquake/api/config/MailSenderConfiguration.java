package com.earthquake.api.config;

import com.earthquake.api.entity.ConfigEntity;
import com.earthquake.api.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Objects;
import java.util.Properties;

/**
 * Created by Semih, 27.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Configuration
public class MailSenderConfiguration {

    private final ConfigRepository configRepository;

    private static final String EMAIL_CONFIG = "EMAIL_CONFIG";

    @Autowired
    public MailSenderConfiguration(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Bean
    public JavaMailSender javaMailService() {

        final ConfigEntity config = configRepository.findById(EMAIL_CONFIG).get();

        if (Objects.nonNull(config)) {

            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);

            mailSender.setUsername(config.getSecondValue());
            mailSender.setPassword(config.getValue());
            mailSender.setJavaMailProperties(getMailProperties());

            return mailSender;
        }

        return null;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.socketFactory.fallback", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.starttls.required", "true");
        properties.setProperty("mail.smtp.ssl.enable", "false");
        properties.setProperty("mail.smtp.ssl.trust", "*");
        properties.setProperty("mail.debug", "true");
        return properties;
    }
}

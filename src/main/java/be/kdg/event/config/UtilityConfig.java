package be.kdg.event.config;

import be.kdg.event.utils.DateTimeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilityConfig {
    @Bean
    public DateTimeUtils dateTimeUtils() {
        return new DateTimeUtils();
    }
}
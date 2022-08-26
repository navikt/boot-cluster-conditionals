package no.nav.boot.conditionals;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class Config {

    @ConditionalOnLocalOrTest
    @Bean
    public String local() {
        return "local";
    }

    @ConditionalOnDev
    @Bean
    public String dev() {
        return "dev";
    }

    @ConditionalOnSBS
    @Bean
    public String sbs() {
        return "sbs";
    }

    @ConditionalOnFSS
    @Bean
    public String fss() {
        return "fss";
    }
}

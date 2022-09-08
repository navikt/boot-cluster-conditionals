package no.nav.boot.conditionals

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class Config {
    @ConditionalOnLocalOrTest
    @Bean
    fun local(): String {
        return "local"
    }

    @ConditionalOnDev
    @Bean
    fun dev(): String {
        return "dev"
    }

    @ConditionalOnSBS
    @Bean
    fun sbs(): String {
        return "sbs"
    }

    @ConditionalOnFSS
    @Bean
    fun fss(): String {
        return "fss"
    }
}
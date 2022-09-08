package no.nav.boot.conditionals

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.assertj.AssertableApplicationContext
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
internal class TestClusterConditionals {
    @Test
    fun local() {
        ApplicationContextRunner().withUserConfiguration(Config::class.java)
            .run { context: AssertableApplicationContext ->
                assertThat(context).hasBean("local")
                assertThat(context).doesNotHaveBean("dev")
                assertThat(context).doesNotHaveBean("fss")
                assertThat(context.getBean("local")).isEqualTo("local")
            }
    }

    @Test
    fun dev() {
        ApplicationContextRunner().withPropertyValues(Cluster.NAIS_CLUSTER_NAME + "=" + EnvUtil.DEV_FSS)
            .withUserConfiguration(Config::class.java)
            .run { context: AssertableApplicationContext ->
                assertThat(context).hasBean("dev")
                assertThat(context).doesNotHaveBean("local")
                assertThat(context.getBean("dev")).isEqualTo("dev")
            }
    }

    @Test
    fun fss() {
        ApplicationContextRunner().withPropertyValues(Cluster.NAIS_CLUSTER_NAME + "=" + EnvUtil.DEV_FSS)
            .withUserConfiguration(Config::class.java)
            .run { context: AssertableApplicationContext ->
                assertThat(context).hasBean("fss")
                assertThat(context).doesNotHaveBean("local")
                assertThat(context.getBean("fss")).isEqualTo("fss")
            }
    }
}
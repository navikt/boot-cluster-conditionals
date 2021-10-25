package no.nav.boot.conditionals;

import static no.nav.boot.conditionals.Cluster.NAIS_CLUSTER_NAME;
import static no.nav.boot.conditionals.EnvUtil.DEV_FSS;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class TestClusterConditionals {

    @Test
    void local() {
        new ApplicationContextRunner().withUserConfiguration(Config.class)
                .run(context -> {
                    assertThat(context).hasBean("local");
                    assertThat(context).doesNotHaveBean("dev");
                    assertThat(context).doesNotHaveBean("fss");
                    assertThat(context.getBean("local")).isEqualTo("local");
                });
    }

    @Test
    void dev() {
        new ApplicationContextRunner().withPropertyValues(NAIS_CLUSTER_NAME + "=" + DEV_FSS)
                .withUserConfiguration(Config.class)
                .run(context -> {
                    assertThat(context).hasBean("dev");
                    assertThat(context).doesNotHaveBean("local");
                    assertThat(context.getBean("dev")).isEqualTo("dev");
                });
    }

    @Test
    void fss() {
        new ApplicationContextRunner().withPropertyValues(NAIS_CLUSTER_NAME + "=" + DEV_FSS)
                .withUserConfiguration(Config.class)
                .run(context -> {
                    assertThat(context).hasBean("fss");
                    assertThat(context).doesNotHaveBean("local");
                    assertThat(context.getBean("fss")).isEqualTo("fss");
                });
    }

}

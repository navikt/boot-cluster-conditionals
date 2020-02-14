package no.nav.foreldrepenger.boot.conditionals;

import static no.nav.foreldrepenger.boot.conditionals.Cluster.NAIS_CLUSTER_NAME;
import static no.nav.foreldrepenger.boot.conditionals.EnvUtil.DEV_FSS;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class TestClusterConditionals {

    @Test
    public void local() {
        new ApplicationContextRunner().withUserConfiguration(Config.class)
                .run(context -> {
                    assertThat(context).hasBean("local");
                    assertThat(context.getBean("local")).isEqualTo("local");
                });
    }

    @Test
    public void dev() {
        new ApplicationContextRunner().withPropertyValues(NAIS_CLUSTER_NAME + "=" + DEV_FSS)
                .withUserConfiguration(Config.class)
                .run(context -> {
                    assertThat(context).hasBean("dev");
                    assertThat(context.getBean("dev")).isEqualTo("dev");
                });
    }

    @Test
    public void fss() {
        new ApplicationContextRunner().withPropertyValues(NAIS_CLUSTER_NAME + "=" + DEV_FSS)
                .withUserConfiguration(Config.class)
                .run(context -> {
                    assertThat(context).hasBean("fss");
                    assertThat(context.getBean("fss")).isEqualTo("fss");
                });
    }
}

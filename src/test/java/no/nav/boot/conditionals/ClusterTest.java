package no.nav.boot.conditionals;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ClusterTest {

    @Test
    void metoderBruktITestEllerDevMåIkkeInneholderProdCluster() {
        assertThat(Cluster.devClusters()).doesNotContain(Cluster.PROD_FSS, Cluster.PROD_SBS, Cluster.PROD_FSS);
        assertThat(Cluster.notProdClusters()).doesNotContain(Cluster.PROD_FSS, Cluster.PROD_SBS, Cluster.PROD_FSS);
        assertThat(Cluster.localOrTest()).doesNotContain(Cluster.PROD_FSS, Cluster.PROD_SBS, Cluster.PROD_FSS);
    }

}

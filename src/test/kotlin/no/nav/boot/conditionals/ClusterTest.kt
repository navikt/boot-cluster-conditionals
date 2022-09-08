package no.nav.boot.conditionals

import no.nav.boot.conditionals.Cluster.Companion.devClusters
import no.nav.boot.conditionals.Cluster.Companion.localOrTest
import no.nav.boot.conditionals.Cluster.Companion.notProdClusters
import no.nav.boot.conditionals.Cluster.PROD_FSS
import no.nav.boot.conditionals.Cluster.PROD_SBS
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
internal class ClusterTest {
    @Test
    fun metoderBruktITestEllerDevMåIkkeInneholderProdCluster() {
        assertThat(devClusters()).doesNotContain(PROD_FSS, PROD_SBS, PROD_FSS)
        assertThat(notProdClusters()).doesNotContain(PROD_FSS, PROD_SBS, PROD_FSS)
        assertThat(localOrTest()).doesNotContain(PROD_FSS, PROD_SBS, PROD_FSS)
    }
}
package no.nav.boot.conditionals

import no.nav.boot.conditionals.Cluster.Companion.notProdClusters
import org.springframework.core.type.AnnotatedTypeMetadata

class OnDevOrLocalCondition : OnClusterCondition() {
    override fun clusters(md: AnnotatedTypeMetadata) = notProdClusters()
}
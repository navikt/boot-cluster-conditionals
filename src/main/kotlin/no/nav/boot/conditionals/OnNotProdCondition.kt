package no.nav.boot.conditionals

import no.nav.boot.conditionals.Cluster.Companion.notProdClusters
import org.springframework.core.type.AnnotatedTypeMetadata

class OnNotProdCondition : OnClusterCondition() {
    override fun clusters(md: AnnotatedTypeMetadata) = notProdClusters()
}
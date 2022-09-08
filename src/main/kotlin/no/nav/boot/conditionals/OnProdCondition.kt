package no.nav.boot.conditionals

import no.nav.boot.conditionals.Cluster.Companion.prodClusters
import org.springframework.core.type.AnnotatedTypeMetadata

class OnProdCondition : OnClusterCondition() {
    override fun clusters(md: AnnotatedTypeMetadata) = prodClusters()
}
package no.nav.boot.conditionals

import no.nav.boot.conditionals.Cluster.Companion.devClusters
import org.springframework.core.type.AnnotatedTypeMetadata

class OnDevCondition : OnClusterCondition() {
    override fun clusters(md: AnnotatedTypeMetadata) = devClusters()
}
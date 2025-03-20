package no.nav.boot.conditionals

import no.nav.boot.conditionals.Cluster.Companion.notDevClusters
import org.springframework.core.type.AnnotatedTypeMetadata

class OnNotDevCondition : OnClusterCondition() {
    override fun clusters(md: AnnotatedTypeMetadata) = notDevClusters()
}
package no.nav.boot.conditionals

import no.nav.boot.conditionals.Cluster.Companion.gcpClusters
import org.springframework.core.type.AnnotatedTypeMetadata

class OnGCPCondition : OnClusterCondition() {
    override fun clusters(md: AnnotatedTypeMetadata) = gcpClusters()
}
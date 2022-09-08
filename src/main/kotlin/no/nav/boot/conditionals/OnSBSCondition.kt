package no.nav.boot.conditionals

import no.nav.boot.conditionals.Cluster.Companion.sbsClusters
import org.springframework.core.type.AnnotatedTypeMetadata

class OnSBSCondition : OnClusterCondition() {
    override fun clusters(md: AnnotatedTypeMetadata) = sbsClusters()
}
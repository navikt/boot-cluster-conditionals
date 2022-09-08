package no.nav.boot.conditionals

import no.nav.boot.conditionals.Cluster.Companion.fssClusters
import org.springframework.core.type.AnnotatedTypeMetadata

class OnFSSCondition : OnClusterCondition() {
    override fun clusters(md: AnnotatedTypeMetadata) = fssClusters()
}
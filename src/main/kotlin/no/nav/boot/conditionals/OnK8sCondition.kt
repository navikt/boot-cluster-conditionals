package no.nav.boot.conditionals

import no.nav.boot.conditionals.Cluster.Companion.k8sClusters
import org.springframework.core.type.AnnotatedTypeMetadata

class OnK8sCondition : OnClusterCondition() {
    override fun clusters(md: AnnotatedTypeMetadata) = k8sClusters()
}
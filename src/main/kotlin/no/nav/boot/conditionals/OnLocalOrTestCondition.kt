package no.nav.boot.conditionals

import no.nav.boot.conditionals.Cluster.Companion.localOrTest
import org.springframework.core.type.AnnotatedTypeMetadata

class OnLocalOrTestCondition : OnClusterCondition() {
    override fun clusters(md: AnnotatedTypeMetadata) = localOrTest()
}
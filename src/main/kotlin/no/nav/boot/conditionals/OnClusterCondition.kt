package no.nav.boot.conditionals

import org.springframework.boot.autoconfigure.condition.ConditionMessage.forCondition
import org.springframework.boot.autoconfigure.condition.ConditionOutcome.match
import org.springframework.boot.autoconfigure.condition.ConditionOutcome.noMatch
import org.springframework.boot.autoconfigure.condition.SpringBootCondition
import org.springframework.context.annotation.ConditionContext
import org.springframework.core.type.AnnotatedTypeMetadata

open class OnClusterCondition : SpringBootCondition() {
    override fun getMatchOutcome(ctx: ConditionContext, md: AnnotatedTypeMetadata) =
        with(clusters(md)) {
            val message = forCondition(ConditionalOnClusters::class.java)
            filter { it.isActive(ctx.environment) }
                .map { match(message.foundExactly(it.clusterName())) }
                .firstOrNull()
                ?: noMatch(message.because(contentToString()))
        }

    protected open fun clusters(md: AnnotatedTypeMetadata) = md.getAnnotationAttributes(ConditionalOnClusters::class.java.name)?.get("clusters") as Array<Cluster>

}
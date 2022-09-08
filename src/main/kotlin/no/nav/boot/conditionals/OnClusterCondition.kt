package no.nav.boot.conditionals

import org.springframework.boot.autoconfigure.condition.ConditionMessage
import org.springframework.boot.autoconfigure.condition.ConditionOutcome
import org.springframework.boot.autoconfigure.condition.SpringBootCondition
import org.springframework.context.annotation.ConditionContext
import org.springframework.core.type.AnnotatedTypeMetadata
import java.util.*

open class OnClusterCondition : SpringBootCondition() {
    override fun getMatchOutcome(ctx: ConditionContext, md: AnnotatedTypeMetadata): ConditionOutcome {
        val message = ConditionMessage.forCondition(ConditionalOnClusters::class.java)
        val clusters = clusters(md)
        return clusters
            ?.filter { c: Cluster -> c.isActive(ctx.environment) }
            ?.map { c: Cluster -> ConditionOutcome.match(message.foundExactly(c.clusterName())) }
            ?.firstOrNull()
            ?: ConditionOutcome.noMatch(message.because(Arrays.toString(clusters)))
    }

    protected open fun clusters(md: AnnotatedTypeMetadata): Array<Cluster> {
        return md.getAnnotationAttributes(ConditionalOnClusters::class.java.name)?.get("clusters") as Array<Cluster>
    }
}
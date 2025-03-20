package no.nav.boot.conditionals

import org.springframework.stereotype.Service
import java.lang.annotation.Inherited

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
@MustBeDocumented
@Service
@ConditionalOnClusters([Cluster.PROD_GCP, Cluster.PROD_FSS, Cluster.TEST, Cluster.LOCAL])
annotation class ConditionalOnNotDev
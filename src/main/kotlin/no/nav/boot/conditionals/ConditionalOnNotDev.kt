package no.nav.boot.conditionals

import org.springframework.context.annotation.Conditional
import org.springframework.stereotype.Component
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*

@Target(ANNOTATION_CLASS, CLASS, FUNCTION)
@Retention(RUNTIME)
@MustBeDocumented
@Component
@Conditional(OnNotDevCondition::class)
annotation class ConditionalOnNotDev
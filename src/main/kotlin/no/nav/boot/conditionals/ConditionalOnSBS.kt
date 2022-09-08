package no.nav.boot.conditionals

import org.springframework.context.annotation.Conditional
import org.springframework.stereotype.Component
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.ANNOTATION_CLASS
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION

@Target(ANNOTATION_CLASS, CLASS, FUNCTION)
@Retention(RUNTIME)
@MustBeDocumented
@Component
@Conditional(OnSBSCondition::class)
annotation class ConditionalOnSBS 
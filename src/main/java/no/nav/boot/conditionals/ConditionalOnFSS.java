package no.nav.boot.conditionals;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( {TYPE, METHOD})
@Retention(RUNTIME)
@Documented
@Component
@Conditional(OnFSSCondition.class)
public @interface ConditionalOnFSS {

}

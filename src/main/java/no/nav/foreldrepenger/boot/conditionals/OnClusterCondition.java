package no.nav.foreldrepenger.boot.conditionals;

import static org.springframework.boot.autoconfigure.condition.ConditionMessage.forCondition;
import static org.springframework.boot.autoconfigure.condition.ConditionOutcome.match;
import static org.springframework.boot.autoconfigure.condition.ConditionOutcome.noMatch;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnClusterCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext ctx, AnnotatedTypeMetadata metadata) {
        var message = forCondition(ConditionalOnClusters.class);
        var clusters = clusters(metadata);

        return safeStream(clusters)
                .filter(c -> c.isActive(ctx.getEnvironment()))
                .map(c -> match(message.foundExactly(c.clusterName())))
                .findFirst()
                .orElseGet(() -> noMatch(message.because(Arrays.toString(clusters))));
    }

    protected Cluster[] clusters(AnnotatedTypeMetadata md) {
        return Cluster[].class.cast(md.getAnnotationAttributes(ConditionalOnClusters.class.getName()).get("clusters"));
    }

    private static <T> Stream<T> safeStream(T... elems) {
        return safeStream(Arrays.asList(elems));
    }

    private static <T> Stream<T> safeStream(List<T> list) {
        return Optional.ofNullable(list)
                .orElseGet(Collections::emptyList)
                .stream();
    }

}

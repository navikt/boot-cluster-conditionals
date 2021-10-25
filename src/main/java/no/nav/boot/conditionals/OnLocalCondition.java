package no.nav.boot.conditionals;

import static no.nav.boot.conditionals.Cluster.local;

import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnLocalCondition extends OnClusterCondition {

    @Override
    protected Cluster[] clusters(AnnotatedTypeMetadata md) {
        return local();
    }
}
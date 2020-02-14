package no.nav.foreldrepenger.boot.conditionals;

import static no.nav.foreldrepenger.boot.conditionals.Cluster.devClusters;

import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnDevCondition extends OnClusterCondition {

    @Override
    protected Cluster[] clusters(AnnotatedTypeMetadata md) {
        return devClusters();
    }
}
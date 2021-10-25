package no.nav.boot.conditionals;

import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnSBSCondition extends OnClusterCondition {

    @Override
    protected Cluster[] clusters(AnnotatedTypeMetadata md) {
        return Cluster.sbsClusters();
    }
}
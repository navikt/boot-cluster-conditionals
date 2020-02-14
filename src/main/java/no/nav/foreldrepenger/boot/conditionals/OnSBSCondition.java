package no.nav.foreldrepenger.boot.conditionals;

import static no.nav.foreldrepenger.boot.conditionals.Cluster.sbsClusters;

import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnSBSCondition extends OnClusterCondition {

    @Override
    protected Cluster[] clusters(AnnotatedTypeMetadata md) {
        return sbsClusters();
    }
}
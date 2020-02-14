package no.nav.foreldrepenger.boot.conditionals;

import static no.nav.foreldrepenger.boot.conditionals.Cluster.fssClusters;

import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnFSSCondition extends OnClusterCondition {

    @Override
    protected Cluster[] clusters(AnnotatedTypeMetadata md) {
        return fssClusters();
    }
}
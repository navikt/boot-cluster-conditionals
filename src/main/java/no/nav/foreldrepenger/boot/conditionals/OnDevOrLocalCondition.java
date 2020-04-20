package no.nav.foreldrepenger.boot.conditionals;

import static no.nav.foreldrepenger.boot.conditionals.Cluster.notProdClusters;

import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnDevOrLocalCondition extends OnClusterCondition {

    @Override
    protected Cluster[] clusters(AnnotatedTypeMetadata md) {
        return notProdClusters();
    }
}
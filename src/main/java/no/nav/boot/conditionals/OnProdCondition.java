package no.nav.boot.conditionals;

import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnProdCondition extends OnClusterCondition {

    @Override
    protected Cluster[] clusters(AnnotatedTypeMetadata md) {
        return Cluster.prodClusters();
    }
}
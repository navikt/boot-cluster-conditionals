package no.nav.foreldrepenger.boot.conditionals;

import static no.nav.foreldrepenger.boot.conditionals.Cluster.prodClusters;

import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnProdCondition extends OnClusterCondition {

    @Override
    protected Cluster[] clusters(AnnotatedTypeMetadata md) {
        return prodClusters();
    }
}
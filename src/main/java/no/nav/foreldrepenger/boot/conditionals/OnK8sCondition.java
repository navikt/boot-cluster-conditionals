package no.nav.foreldrepenger.boot.conditionals;

import static no.nav.foreldrepenger.boot.conditionals.Cluster.k8sClusters;

import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnK8sCondition extends OnClusterCondition {

    @Override
    protected Cluster[] clusters(AnnotatedTypeMetadata md) {
        return k8sClusters();
    }
}
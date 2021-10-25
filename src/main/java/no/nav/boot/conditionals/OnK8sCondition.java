package no.nav.boot.conditionals;

import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnK8sCondition extends OnClusterCondition {

    @Override
    protected Cluster[] clusters(AnnotatedTypeMetadata md) {
        return Cluster.k8sClusters();
    }
}
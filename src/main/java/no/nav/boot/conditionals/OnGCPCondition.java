package no.nav.boot.conditionals;

import static no.nav.boot.conditionals.Cluster.gcpClusters;

import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnGCPCondition extends OnClusterCondition {

    @Override
    protected Cluster[] clusters(AnnotatedTypeMetadata md) {
        return gcpClusters();
    }
}
package no.nav.boot.conditionals;

import static no.nav.boot.conditionals.Cluster.vtp;

import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnVTPCondition extends OnClusterCondition {

    @Override
    protected Cluster[] clusters(AnnotatedTypeMetadata md) {
        return vtp();
    }
}
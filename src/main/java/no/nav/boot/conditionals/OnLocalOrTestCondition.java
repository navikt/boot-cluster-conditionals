package no.nav.boot.conditionals;

import static no.nav.boot.conditionals.Cluster.localOrTest;

import org.springframework.core.type.AnnotatedTypeMetadata;

public class OnLocalOrTestCondition extends OnClusterCondition {

    @Override
    protected Cluster[] clusters(AnnotatedTypeMetadata md) {
        return localOrTest();
    }
}

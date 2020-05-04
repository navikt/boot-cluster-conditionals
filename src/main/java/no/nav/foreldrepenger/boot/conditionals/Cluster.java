package no.nav.foreldrepenger.boot.conditionals;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

public enum Cluster {
    LOCAL(EnvUtil.LOCAL),
    DEV_SBS(EnvUtil.DEV_SBS),
    DEV_FSS(EnvUtil.DEV_FSS),
    DEV_GCP(EnvUtil.DEV_GCP),
    PROD_GCP(EnvUtil.PROD_GCP),
    PROD_FSS(EnvUtil.PROD_FSS),
    PROD_SBS(EnvUtil.PROD_SBS);

    public static final String NAIS_CLUSTER_NAME = "NAIS_CLUSTER_NAME";
    public static final String NAIS_NAMESPACE_NAME = "NAIS_NAMESPACE_NAME";

    private static final Logger LOG = LoggerFactory.getLogger(Cluster.class);

    private final String clusterName;

    Cluster(String clusterName) {
        this.clusterName = clusterName;
    }

    public String clusterName() {
        return clusterName;
    }

    public boolean isActive(Environment env, String... namespaceNames) {

        return isClusterActive(env)
                && isNamespaceActive(env, namespaceNames);
    }

    private boolean isNamespaceActive(Environment env, String... namespaceNames) {
        var namespace = namespace(env);
        LOG.trace("Sjekker om current namespace {} er blant {}", namespace, Arrays.toString(namespaceNames));
        if (namespaceNames.length == 0) {
            return true;
        }

        var aktiv = Arrays.stream(namespaceNames)
                .filter(n -> n.equals(namespace))
                .findAny();
        LOG.trace("Namespace {} i {} er {}", namespace, clusterName(), aktiv.isPresent() ? "aktivt" : "ikke aktivt");

        return aktiv.isPresent();
    }

    private String namespace(Environment env) {
        return env.getProperty(NAIS_NAMESPACE_NAME);
    }

    public boolean isClusterActive(Environment env) {
        var aktiv = env.getProperty(NAIS_CLUSTER_NAME, EnvUtil.LOCAL).equals(clusterName);
        if (aktiv) {
            LOG.trace("Cluster {} er aktivt", clusterName());
        }
        return aktiv;
    }

    public static Cluster[] prodClusters() {
        return new Cluster[] { PROD_SBS, PROD_GCP, PROD_FSS };
    }

    public static Cluster[] devClusters() {
        return new Cluster[] { DEV_SBS, DEV_GCP, DEV_FSS };
    }

    public static Cluster[] sbsClusters() {
        return new Cluster[] { DEV_SBS, PROD_SBS };
    }

    public static Cluster[] fssClusters() {
        return new Cluster[] { DEV_FSS, PROD_FSS };
    }

    public static Cluster[] notProdClusters() {
        return new Cluster[] { DEV_SBS, DEV_GCP, DEV_FSS, LOCAL };
    }

    public static Cluster[] k8sClusters() {
        return new Cluster[] { DEV_SBS, DEV_FSS, DEV_GCP, PROD_GCP, PROD_GCP };
    }

    public static Cluster[] gcpClusters() {
        return new Cluster[] { DEV_GCP, PROD_GCP };
    }

    public static Cluster[] local() {
        return new Cluster[] { LOCAL };
    }

}

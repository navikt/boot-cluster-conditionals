package no.nav.foreldrepenger.boot.conditionals;

import static java.lang.System.getenv;
import static no.nav.foreldrepenger.boot.conditionals.EnvUtil.DEV;
import static no.nav.foreldrepenger.boot.conditionals.EnvUtil.PROD;

import java.util.Arrays;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

public enum Cluster {
    VTP(EnvUtil.VTP),
    TEST(EnvUtil.TEST),
    LOCAL(EnvUtil.LOCAL),
    DEV_SBS(EnvUtil.DEV_SBS),
    DEV_FSS(EnvUtil.DEV_FSS),
    DEV_GCP(EnvUtil.DEV_GCP),
    PROD_GCP(EnvUtil.PROD_GCP),
    PROD_FSS(EnvUtil.PROD_FSS),
    PROD_SBS(EnvUtil.PROD_SBS);

    public static final String NAIS_CLUSTER_NAME = "NAIS_CLUSTER_NAME";
    public static final String NAIS_NAMESPACE_NAME = "NAIS_NAMESPACE";
    public static final String NAIS_IMAGE_NAME = "NAIS_APP_IMAGE";

    private static final Logger LOG = LoggerFactory.getLogger(Cluster.class);

    private final String clusterName;

    Cluster(String clusterName) {
        this.clusterName = clusterName;
    }

    public String clusterName() {
        return clusterName;
    }

    public static String currentNamespace() {
        return Optional.ofNullable(namespace())
                .orElse("default");
    }

    public static Cluster currentCluster() {
        String name = cluster();
        return Arrays.stream(values())
                .filter(e -> e.clusterName.equals(name))
                .findFirst()
                .orElseGet(() -> Cluster.LOCAL);
    }

    public boolean isActive(Environment env) {
        return isClusterActive(env);
    }

    public static String[] profiler() {
        return profilerFraCluster(cluster());
    }

    private static String cluster() {
        return getenv(NAIS_CLUSTER_NAME);
    }

    private static String namespace() {
        return getenv(NAIS_NAMESPACE_NAME);
    }

    private static String[] profilerFraCluster(String cluster) {
        if (cluster == null) {
            LOG.info("NAIS cluster ikke detektert, antar {}", LOCAL);
            System.setProperty(NAIS_CLUSTER_NAME, EnvUtil.LOCAL);
            return new String[] { EnvUtil.LOCAL };
        }

        if (cluster.equals(EnvUtil.TEST)) {
            System.setProperty(NAIS_CLUSTER_NAME, EnvUtil.TEST);
            return new String[] { EnvUtil.TEST };
        }
        if (cluster.equals(EnvUtil.DEV_SBS)) {
            return new String[] { DEV, EnvUtil.DEV_SBS };
        }
        if (cluster.equals(EnvUtil.DEV_GCP)) {
            return new String[] { DEV, EnvUtil.DEV_GCP };
        }
        if (cluster.equals(EnvUtil.PROD_GCP)) {
            return new String[] { PROD, EnvUtil.PROD_GCP };
        }
        if (cluster.equals(EnvUtil.PROD_SBS)) {
            return new String[] { PROD, EnvUtil.PROD_SBS };
        }
        if (cluster.equals(EnvUtil.DEV_FSS)) {
            return new String[] { DEV, EnvUtil.DEV_FSS };
        }
        if (cluster.equals(EnvUtil.PROD_FSS)) {
            return new String[] { PROD, EnvUtil.PROD_FSS };
        }
        return new String[] { cluster };
    }

    public boolean isClusterActive(Environment env) {
        return env.getProperty(NAIS_CLUSTER_NAME, EnvUtil.LOCAL).equals(clusterName);
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
        return new Cluster[] { DEV_SBS, DEV_FSS, DEV_GCP, PROD_FSS, PROD_SBS, PROD_GCP };
    }

    public static Cluster[] gcpClusters() {
        return new Cluster[] { DEV_GCP, PROD_GCP };
    }

    public static Cluster[] local() {
        return new Cluster[] { LOCAL };
    }

    public static Cluster[] vtp() {
        return new Cluster[] { VTP };
    }

}

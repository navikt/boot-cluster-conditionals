package no.nav.boot.conditionals

import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment

enum class Cluster(private val clusterName: String) {
    TEST(EnvUtil.TEST),
    LOCAL(EnvUtil.LOCAL),
    DEV_SBS(EnvUtil.DEV_SBS),
    DEV_FSS(EnvUtil.DEV_FSS),
    DEV_GCP(EnvUtil.DEV_GCP),
    PROD_GCP(EnvUtil.PROD_GCP),
    PROD_FSS(EnvUtil.PROD_FSS),
    PROD_SBS(EnvUtil.PROD_SBS);

    fun clusterName() = clusterName
    fun isActive(env: Environment)= isClusterActive(env)
    private fun isClusterActive(env: Environment) = env.getProperty(NAIS_CLUSTER_NAME, EnvUtil.LOCAL) == clusterName

    companion object {
        const val NAIS_CLUSTER_NAME = "NAIS_CLUSTER_NAME"
        const val NAIS_NAMESPACE_NAME = "NAIS_NAMESPACE"
        const val NAIS_IMAGE_NAME = "NAIS_APP_IMAGE"
        private val LOG = LoggerFactory.getLogger(Cluster::class.java)
        fun currentNamespace() = namespace() ?: "default"

        fun currentCluster() =
            values().firstOrNull() { it.clusterName == cluster() } ?: LOCAL


        fun profiler()  = profilerFraCluster(cluster())


        private fun cluster() = System.getenv(NAIS_CLUSTER_NAME)


        private fun namespace() = System.getenv(NAIS_NAMESPACE_NAME)


        private fun profilerFraCluster(cluster: String?) =
            when(cluster) {
                EnvUtil.TEST -> { System.setProperty(NAIS_CLUSTER_NAME, EnvUtil.TEST)
                 arrayOf(EnvUtil.TEST)
               }
                EnvUtil.DEV_SBS ->  arrayOf(EnvUtil.DEV, EnvUtil.DEV_SBS, EnvUtil.SBS)
                EnvUtil.DEV_GCP ->  arrayOf(EnvUtil.DEV, EnvUtil.DEV_GCP, EnvUtil.GCP)
                EnvUtil.PROD_GCP -> arrayOf(EnvUtil.PROD, EnvUtil.PROD_GCP, EnvUtil.GCP)
                EnvUtil.PROD_SBS -> arrayOf(EnvUtil.PROD, EnvUtil.PROD_SBS, EnvUtil.SBS)
                EnvUtil.DEV_FSS -> arrayOf(EnvUtil.DEV, EnvUtil.DEV_FSS, EnvUtil.FSS)
                EnvUtil.PROD_FSS -> arrayOf(EnvUtil.PROD, EnvUtil.PROD_FSS, EnvUtil.FSS)
                null -> {
                    LOG.info("NAIS cluster ikke detektert, antar ${LOCAL}")
                    System.setProperty(NAIS_CLUSTER_NAME, EnvUtil.LOCAL)
                    arrayOf(EnvUtil.LOCAL)
                }
                else -> arrayOf(cluster)
            }

        fun prodClusters() = arrayOf(PROD_SBS, PROD_GCP, PROD_FSS)

        fun devClusters() = arrayOf(DEV_SBS, DEV_GCP, DEV_FSS)

        fun sbsClusters() = arrayOf(DEV_SBS, PROD_SBS)

        fun fssClusters() = arrayOf(DEV_FSS, PROD_FSS)

        fun gcpClusters() = arrayOf(DEV_GCP, PROD_GCP)

        fun notProdClusters() = arrayOf(DEV_SBS, DEV_GCP, DEV_FSS, LOCAL, TEST)

        fun k8sClusters() = arrayOf(DEV_SBS, DEV_FSS, DEV_GCP, PROD_FSS, PROD_SBS, PROD_GCP)

        fun localOrTest() = arrayOf(LOCAL, TEST)
    }
}
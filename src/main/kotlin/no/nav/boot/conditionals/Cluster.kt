package no.nav.boot.conditionals

import java.lang.System.*
import org.slf4j.LoggerFactory
import org.slf4j.LoggerFactory.*
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
        private val LOG = getLogger(Cluster::class.java)
        @JvmStatic
        fun currentNamespace() = namespace() ?: "default"

        @JvmStatic
        fun currentCluster() =
            values().firstOrNull() { it.clusterName == cluster() } ?: LOCAL

        @JvmStatic
        val currentCluster = currentCluster()
        @JvmStatic
        fun profiler()  = profilerFraCluster(cluster())


        private fun cluster() = getenv(NAIS_CLUSTER_NAME) ?: EnvUtil.LOCAL


        private fun namespace() = getenv(NAIS_NAMESPACE_NAME) ?: EnvUtil.LOCAL


        private fun profilerFraCluster(cluster: String) =
            when(cluster) {
                EnvUtil.TEST -> { setProperty(NAIS_CLUSTER_NAME, EnvUtil.TEST)
                 arrayOf(EnvUtil.TEST)
               }
                EnvUtil.LOCAL -> { setProperty(NAIS_CLUSTER_NAME, EnvUtil.LOCAL)
                    arrayOf(EnvUtil.LOCAL)
                }
                EnvUtil.DEV_SBS ->  arrayOf(EnvUtil.DEV, EnvUtil.DEV_SBS, EnvUtil.SBS)
                EnvUtil.DEV_GCP ->  arrayOf(EnvUtil.DEV, EnvUtil.DEV_GCP, EnvUtil.GCP)
                EnvUtil.PROD_GCP -> arrayOf(EnvUtil.PROD, EnvUtil.PROD_GCP, EnvUtil.GCP)
                EnvUtil.PROD_SBS -> arrayOf(EnvUtil.PROD, EnvUtil.PROD_SBS, EnvUtil.SBS)
                EnvUtil.DEV_FSS -> arrayOf(EnvUtil.DEV, EnvUtil.DEV_FSS, EnvUtil.FSS)
                EnvUtil.PROD_FSS -> arrayOf(EnvUtil.PROD, EnvUtil.PROD_FSS, EnvUtil.FSS)
                else -> arrayOf(cluster)
            }

        fun isProd() = currentCluster() in prodClusters()

        fun isNotProd() = currentCluster() in notProdClusters()

        fun isDev() = currentCluster() in devClusters()

        fun isGCP() = currentCluster() in gcpClusters()

        fun isFss() = currentCluster() in fssClusters()


        fun prodClusters() = arrayOf(PROD_SBS, PROD_GCP, PROD_FSS)

        fun devClusters() = arrayOf(DEV_SBS, DEV_GCP, DEV_FSS)

        fun sbsClusters() = arrayOf(DEV_SBS, PROD_SBS)

        fun fssClusters() = arrayOf(DEV_FSS, PROD_FSS)

        fun gcpClusters() = arrayOf(DEV_GCP, PROD_GCP)

        fun notDevClusters() = arrayOf(PROD_SBS, PROD_GCP, PROD_FSS, LOCAL, TEST)

        fun notProdClusters() = arrayOf(DEV_SBS, DEV_GCP, DEV_FSS, LOCAL, TEST)

        fun k8sClusters() = arrayOf(DEV_SBS, DEV_FSS, DEV_GCP, PROD_FSS, PROD_SBS, PROD_GCP)

        fun localOrTest() = arrayOf(LOCAL, TEST)
    }
}
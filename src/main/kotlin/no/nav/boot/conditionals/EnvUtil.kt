package no.nav.boot.conditionals

import org.slf4j.MarkerFactory
import org.springframework.core.env.Environment
import org.springframework.core.env.Profiles

object EnvUtil {
    val CONFIDENTIAL = MarkerFactory.getMarker("CONFIDENTIAL")
    const val FSS = "fss"
    const val SBS = "sbs"
    const val LOCAL = "local"
    const val DEFAULT = "default"
    const val GCP = "gcp"
    const val TEST = "test"
    const val DEV = "dev"
    const val PROD = "prod"
    val DEV_GCP = join(DEV, GCP)
    val PROD_GCP = join(PROD, GCP)
    val PROD_SBS = join(PROD, SBS)
    val DEV_SBS = join(DEV, SBS)
    val PROD_FSS = join(PROD, FSS)
    val DEV_FSS = join(DEV, FSS)
    const val VTP = "vtp"
    private fun join(env: String, cluster: String) = "$env-$cluster"

    @JvmStatic
    fun isDevOrLocal(env: Environment) = isLocal(env) || isDev(env)

    @JvmStatic
    fun isVTP(env: Environment) = env.acceptsProfiles(Profiles.of(VTP))

    @JvmStatic
    fun isDev(env: Environment) = env.acceptsProfiles(Profiles.of(DEV_SBS, DEV_GCP, DEV_FSS))

    @JvmStatic
    fun isProd(env: Environment) = env.acceptsProfiles(Profiles.of(PROD_SBS, PROD_FSS,PROD_GCP))

    @JvmStatic
    fun isGcp(env: Environment) = env.acceptsProfiles(Profiles.of(DEV_GCP, PROD_GCP))

    @JvmStatic
    fun isLocal(env: Environment?) = env == null || env.acceptsProfiles(Profiles.of(LOCAL))

}
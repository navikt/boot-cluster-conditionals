package no.nav.foreldrepenger.boot.conditionals;

import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

public final class EnvUtil {

    public static final String TEST = "test";
    public static final String DEV_GCP = "dev-gcp";
    public static final String PROD_GCP = "prod-gcp";

    public static final String LOCAL = "local";
    public static final String DEFAULT = "default";

    public static final String PROD_SBS = "prod-sbs";
    public static final String DEV_SBS = "dev-sbs";
    public static final String PROD_FSS = "prod-fss";
    public static final String DEV_FSS = "dev-fss";

    private EnvUtil() {

    }

    public static boolean isDevOrLocal(Environment env) {
        return isLocal(env) || isDev(env);
    }

    private static boolean isDev(Environment env) {
        return env.acceptsProfiles(Profiles.of(DEV_SBS, DEV_GCP, DEV_FSS));
    }

    private static boolean isLocal(Environment env) {
        return (env == null) || env.acceptsProfiles(Profiles.of(LOCAL));
    }
}

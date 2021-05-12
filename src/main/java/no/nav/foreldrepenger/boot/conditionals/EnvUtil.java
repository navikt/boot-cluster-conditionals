package no.nav.foreldrepenger.boot.conditionals;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

public final class EnvUtil {

    public static final Marker CONFIDENTIAL = MarkerFactory.getMarker("CONFIDENTIAL");

    private static final String FSS = "-fss";
    private static final String SBS = "-sbs";
    private static final String GCP = "-gcp";
    public static final String LOCAL = "local";
    public static final String DEFAULT = "default";

    public static final String TEST = "test";
    public static final String DEV = "dev";
    public static final String PROD = "prod";
    public static final String DEV_GCP = DEV + GCP;
    public static final String PROD_GCP = PROD + GCP;

    public static final String PROD_SBS = PROD + SBS;
    public static final String DEV_SBS = DEV + SBS;
    public static final String PROD_FSS = PROD + FSS;
    public static final String DEV_FSS = DEV + FSS;
    public static final String VTP = "vtp";

    private EnvUtil() {

    }

    public static boolean isDevOrLocal(Environment env) {
        return isLocal(env) || isDev(env);
    }

    public static boolean isVTP(Environment env) {
        return env.acceptsProfiles(Profiles.of(VTP));
    }

    public static boolean isDev(Environment env) {
        return env.acceptsProfiles(Profiles.of(DEV_SBS, DEV_GCP, DEV_FSS));
    }

    public static boolean isLocal(Environment env) {
        return (env == null) || env.acceptsProfiles(Profiles.of(LOCAL));
    }
}

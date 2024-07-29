package eg.pharma.api.helpers.utils;

import java.util.Objects;

public class EnvironmentUtil {

    static final String TEST = "test";
    static final String DEVELOPMENT = "development";
    static final String PRODUCTION = "production";
    private static String current = null;

    public static void setCurrent(String env) {
        current = env;
    }

    public static String getCurrent() {
        return current;
    }

    public static boolean isProduction() {
        return Objects.equals(current, PRODUCTION);
    }

    public static boolean isDevelopment() {
        return Objects.equals(current, DEVELOPMENT);
    }

    public static boolean isTest() {
        return Objects.equals(current, TEST);
    }
}

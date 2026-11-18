package tn.esprit.autoloc;

public final class Utils {

    private Utils() {
    }

    public static boolean isNotEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }
}
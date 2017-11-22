package fr.soat.soadle.doodle;

import static java.util.Objects.requireNonNull;

/**
 * a {@link Doodle} is a class representation of poll made with the https://doodle.com/ site
 */
public class Doodle {

    private final static String DOODLE_URL_START = "https://doodle.com/poll/";

    private final String uri;

    private Doodle(String uri) {
        this.uri = requireNonEmpty(uri);
    }

    private String requireNonEmpty(String uri) {
        if(requireNonNull(uri, "a doodle uri cannot be null").isEmpty()) {
            throw new IllegalArgumentException("a doodle uri cannot be empty");
        }
        return  uri;
    }

    public static Doodle withUri(String uri) {
        return new Doodle(uri);
    }

    public String getUrl() {
        return DOODLE_URL_START + uri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doodle doodle = (Doodle) o;

        return uri.equals(doodle.uri);
    }

    @Override
    public int hashCode() {
        return uri.hashCode();
    }
}

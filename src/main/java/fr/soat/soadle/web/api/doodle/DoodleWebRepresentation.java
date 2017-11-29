package fr.soat.soadle.web.api.doodle;

import fr.soat.soadle.doodle.Doodle;

import java.io.Serializable;
import java.util.function.Function;

/**
 * Dto class for transmitting {@link Doodle} information through a JSON web service end point.
 */
public class DoodleWebRepresentation implements Serializable {

    private final String url;

    private DoodleWebRepresentation(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public static Function<Doodle, DoodleWebRepresentation> mapper() {
        return d -> new DoodleWebRepresentation(d.getUrl());
    }
}

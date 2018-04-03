package space.polarfish.musicmeta.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Genre {
    NONE("None"),
    BLUES("Blues"),
    CLASSICAL("Classical"),
    COUNTRY("Country"),
    ELECTRONIC("Electronic"),
    FOLK("Folk"),
    JAZZ("Jazz"),
    NEW_AGE("New age"),
    REGGAE("Reggae"),
    ROCK("Rock");

    private final String value;

    Genre(String value) {
        this.value = value;
    }

    @JsonValue
    public String toString() {
        return value;
    }

    @JsonCreator
    public static Genre fromString(String genreString) {
        if (genreString != null) {
            for (Genre genre : Genre.values()) {
                if (genre.toString().equalsIgnoreCase(genreString)) {
                    return genre;
                }
            }
        }

        return NONE;
    }
}

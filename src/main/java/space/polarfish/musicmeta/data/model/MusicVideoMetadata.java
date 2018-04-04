package space.polarfish.musicmeta.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import space.polarfish.musicmeta.data.validation.GenreIsValid;
import space.polarfish.musicmeta.data.validation.NullOrNotBlank;
import space.polarfish.musicmeta.data.validation.groups.OnCreate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;

@Entity
@SequenceGenerator(name = "music_video_metadata_sequence", initialValue = 100, allocationSize = 1)
@Table(name = "music_video_metadata")
public class MusicVideoMetadata {
    private Long id;
    private String title;
    private String album;
    private String artist;
    private Integer duration;
    private Genre genre;
    private Set<String> subgenres;
    private Integer releaseYear;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "music_video_metadata_sequence")
    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull(message = "title field is required", groups = OnCreate.class)
    @NullOrNotBlank(message = "title field must not be blank")
    @Column(name = "title")
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NullOrNotBlank(message = "album field must not be blank")
    @Column(name = "album")
    @JsonProperty("album")
    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @NotNull(message = "artist field is required", groups = OnCreate.class)
    @NullOrNotBlank(message = "artist field must not be blank")
    @Column(name = "artist")
    @JsonProperty("artist")
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @NotNull(message = "duration field is required", groups = OnCreate.class)
    @Positive(message = "duration field must be a positive number of seconds")
    @Column(name = "duration")
    @JsonProperty("duration")
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @NotNull(message = "genre field is required", groups = OnCreate.class)
    // TODO the valid values list must be generated dynamically
    @GenreIsValid(message = "Genre is invalid. Valid values are [Blues, Classical, Country, Electronic, Folk, Jazz, New age, Reggae, Rock].")
    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    @JsonProperty("genre")
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "music_video_metadata_subgenre", joinColumns = @JoinColumn(name = "music_video_metadata_id"))
    @Column(name = "subgenre")
    @JsonProperty("subgenres")
    public Set<String> getSubgenres() {
        return subgenres;
    }

    public void setSubgenres(Set<String> subgenres) {
        this.subgenres = subgenres;
    }

    @NotNull(message = "releaseYear field is required", groups = OnCreate.class)
    // 1926 is the minimum, see https://en.wikipedia.org/wiki/Music_video#History_and_development
    @Min(value = 1926, message = "releaseYear field minimum value is 1926")
    // TODO introduce dynamic validation for the max release year
    @Max(value = 2018, message = "releaseYear field maximum value is 2018")
    @Column(name = "release_year")
    @JsonProperty("releaseYear")
    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public MusicVideoMetadata updatePropertiesFromAnotherEntity(MusicVideoMetadata entity) {
        if (entity.getTitle() != null) {
            setTitle(entity.getTitle());
        }

        if (entity.getAlbum() != null) {
            setAlbum(entity.getAlbum());
        }

        if (entity.getArtist() != null) {
            setArtist(entity.getArtist());
        }

        if (entity.getDuration() != null) {
            setDuration(entity.getDuration());
        }

        if (entity.getGenre() != null) {
            setGenre(entity.getGenre());
        }

        if (entity.getSubgenres() != null) {
            setSubgenres(entity.getSubgenres());
        }

        if (entity.getReleaseYear() != null) {
            setReleaseYear(entity.getReleaseYear());
        }

        return this;
    }
}

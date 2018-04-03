package space.polarfish.musicmeta.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import space.polarfish.musicmeta.data.model.Genre;
import space.polarfish.musicmeta.data.model.MusicVideoMetadata;
import space.polarfish.musicmeta.data.service.MusicVideoMetadataService;
import space.polarfish.musicmeta.data.validation.groups.OnCreate;

import javax.validation.Valid;
import javax.validation.groups.Default;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/music-video-metadata",
        consumes = "application/json; charset=UTF-8",
        produces = "application/json; charset=UTF-8")
public class MusicVideoMetadataController {

    @Autowired
    private MusicVideoMetadataService musicVideoMetadataService;

    @PostMapping
    public MusicVideoMetadata create(@Validated({Default.class, OnCreate.class}) @Valid @RequestBody MusicVideoMetadata meta) {
        return musicVideoMetadataService.create(meta);
    }

    @GetMapping("/{id}")
    public MusicVideoMetadata read(@PathVariable("id") Long id) {
        return musicVideoMetadataService.read(id);
    }

    @PutMapping("/{id}")
    public MusicVideoMetadata update(@PathVariable("id") Long id,
                                     @Valid @RequestBody MusicVideoMetadata entity) {

        return musicVideoMetadataService.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        musicVideoMetadataService.delete(id);
    }

    @GetMapping
    public List<MusicVideoMetadata> list(@RequestParam(value = "genre", required = false) String genre,
                                         @RequestParam(value = "subgenre", required = false) String subgenre) {
        return musicVideoMetadataService.list(genre == null ? null : Genre.fromString(genre), subgenre);
    }
}

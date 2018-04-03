package space.polarfish.musicmeta.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.polarfish.musicmeta.data.model.Genre;
import space.polarfish.musicmeta.data.model.MusicVideoMetadata;
import space.polarfish.musicmeta.data.repo.MusicVideoMetadataRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class MusicVideoMetadataService {

    private MusicVideoMetadataRepository repo;

    @Autowired
    public MusicVideoMetadataService(MusicVideoMetadataRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public MusicVideoMetadata create(MusicVideoMetadata entity) {
        return repo.save(entity);
    }

    @Transactional(readOnly = true)
    public MusicVideoMetadata read(Long id) {
        Optional<MusicVideoMetadata> entity = repo.findById(id);

        if (!entity.isPresent()) {
            throw new EntityNotFoundException("Unable to find space.polarfish.musicmeta.data.model.MusicVideoMetadata with id " + id);
        }

        return entity.get();
    }

    @Transactional
    public MusicVideoMetadata update(Long id, MusicVideoMetadata entity) {
        return repo.save(repo.getOne(id).updatePropertiesFromAnotherEntity(entity));
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<MusicVideoMetadata> list(Genre genre, String subgenre) {
        if (genre != null && subgenre != null) {
            return repo.findAllBySubgenreAndGenre(subgenre, genre);
        } else if (genre != null) {
            return repo.findByGenre(genre);
        } else if (subgenre != null) {
            return repo.findAllBySubgenre(subgenre);
        }

        return repo.findAll();
    }
}

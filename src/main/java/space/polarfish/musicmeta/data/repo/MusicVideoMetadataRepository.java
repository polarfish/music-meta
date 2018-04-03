package space.polarfish.musicmeta.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import space.polarfish.musicmeta.data.model.Genre;
import space.polarfish.musicmeta.data.model.MusicVideoMetadata;

import java.util.List;

@Repository
public interface MusicVideoMetadataRepository extends JpaRepository<MusicVideoMetadata, Long> {

    @Query("select m2 from MusicVideoMetadata m2 where m2.id in (select m.id from MusicVideoMetadata m join m.subgenres sg where lower(:#{#subgenre}) = lower(sg))")
    List<MusicVideoMetadata> findAllBySubgenre(@Param("subgenre") String subgenre);

    @Query("select m2 from MusicVideoMetadata m2 where m2.id in (select m.id from MusicVideoMetadata m join m.subgenres sg where lower(:#{#subgenre}) = lower(sg) and m.genre = :#{#genre})")
    List<MusicVideoMetadata> findAllBySubgenreAndGenre(@Param("subgenre") String subgenre, @Param("genre") Genre genre);

    List<MusicVideoMetadata> findByGenre(Genre genre);

}

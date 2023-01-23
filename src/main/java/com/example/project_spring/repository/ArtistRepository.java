package com.example.project_spring.repository;

import com.example.project_spring.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {

    @Query("SELECT artist FROM Artist artist JOIN artist.albums album " +
            "WHERE album.name = :album_name and artist.name = :artist_name")
    List<Artist> findByAlbumNameAndArtistName(@Param("album_name") String albumName, @Param("artist_name") String artistName);
    Optional<Artist> findById(Integer integer);
    Optional<Artist> findByName(String name);
    boolean existsByName(String name);
    List<Artist> findAll();
}

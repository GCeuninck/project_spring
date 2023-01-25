package com.example.project_spring.repository;

import com.example.project_spring.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackRepository  extends JpaRepository<Track, Integer> {

    @Query("From Track track JOIN track.album album " +
            "WHERE album.name = :album_name")
    List<Track> findByAlbumName(@Param("album_name") String albumName);

    @Query("From Track track JOIN track.album album JOIN album.artists artist " +
            "WHERE artist.name = :artist_name")
    List<Track> findByArtistName(@Param("artist_name") String artistName);
    Optional<Track> findById(Integer integer);
    List<Track> findAllByName(String name);
    List<Track> findAll();
}

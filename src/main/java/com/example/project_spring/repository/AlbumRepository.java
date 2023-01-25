package com.example.project_spring.repository;

import com.example.project_spring.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

    @Query("SELECT album FROM Album album JOIN album.artists artist " +
            "WHERE album.name = :album_name and artist.name = :artist_name")
    List<Album> findByAlbumNameAndArtistName(@Param("album_name") String albumName, @Param("artist_name") String artistName);

    @Query("SELECT album FROM Album album JOIN album.artists artist " +
            "WHERE artist.name = :artist_name")
    List<Album> findByArtistName(@Param("artist_name") String artistName);

    Optional<Album> findById(Integer integer);
    List<Album> findAllByName(String name);
    List<Album> findByReleaseDate(Date date);
    List<Album> findByReleaseDateAfter(Date date);
    List<Album> findByReleaseDateBefore(Date date);
    List<Album> findByReleaseDateBetween(Date starDate, Date endDate);

    List<Album> findAll();
}

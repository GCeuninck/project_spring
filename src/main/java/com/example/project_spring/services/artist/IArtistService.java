package com.example.project_spring.services.artist;

import com.example.project_spring.entity.Artist;

import java.util.List;

public interface IArtistService {
    List<Artist> getArtists();
    Artist getArtist(Integer id);
    Artist getArtist(String name);
    List<Artist> getArtistWithAlbum(String albumName, String artistName);
    List<Artist> getArtistWithAlbum(String albumName);
    Integer addArtist(Artist artist);
    Boolean updateArtist(Integer id, Artist artist);
    Boolean deleteArtist(Integer id);
    Boolean deleteArtists();
}

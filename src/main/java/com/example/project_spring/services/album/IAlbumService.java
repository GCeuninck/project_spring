package com.example.project_spring.services.album;

import com.example.project_spring.entity.Album;

import java.sql.Date;
import java.util.List;

public interface IAlbumService {
    List<Album> getAlbums();
    Album getAlbum(Integer id);
    List<Album> getAlbums(String name);
    List<Album> getAlbums(Date date);
    List<Album> getAlbumsByStartDate(Date date);
    List<Album> getAlbumsByEndDate(Date date);
    List<Album> getAlbums(Date startDate, Date endDate);
    List<Album> getAlbumWithArtist(String albumName, String artistName);
    List<Album> getAlbumWithArtist(String artistName);
    Integer addAlbum(Album album);
    Boolean updateAlbum(Integer id, Album album);
    Boolean deleteAlbum(Integer id);
    Boolean deleteAlbums();
}

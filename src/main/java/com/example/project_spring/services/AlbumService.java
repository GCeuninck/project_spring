package com.example.project_spring.services;

import com.example.project_spring.entity.Album;
import com.example.project_spring.entity.Artist;
import com.example.project_spring.repository.AlbumRepository;
import com.example.project_spring.repository.ArtistRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    public List<Album> getAlbums(){
        return albumRepository.findAll();
    }

    public Album getAlbum(Integer id){
        return albumRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Album Not Found with id: " + id));
    }

    public Album getAlbum(String name){
        return albumRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Album Not Found with name: " + name));
    }

    public List<Album> getAlbums(Date date){
        List<Album> albums = albumRepository.findByReleaseDate(date);
        if(albums == null || albums.isEmpty()){
            throw new EntityNotFoundException("Album Not Found with date: " + date.toString());
        }
        return albums;
    }

    public List<Album> getAlbumsByStartDate(Date date){
        List<Album> albums = albumRepository.findByReleaseDateAfter(date);
        if(albums == null || albums.isEmpty()){
            throw new EntityNotFoundException("Album Not Found with start date: " + date.toString());
        }
        return albums;
    }

    public List<Album> getAlbumsByEndDate(Date date){
        List<Album> albums = albumRepository.findByReleaseDateBefore(date);
        if(albums == null || albums.isEmpty()){
            throw new EntityNotFoundException("Album Not Found with end date: " + date.toString());
        }
        return albums;
    }

    public List<Album> getAlbums(Date startDate, Date endDate){
        List<Album> albums = albumRepository.findByReleaseDateBetween(startDate, endDate);
        if(albums == null || albums.isEmpty()){
            throw new EntityNotFoundException("Album Not Found between: " + startDate.toString() + " and: " + endDate.toString());
        }
        return albums;
    }

    public List<Album> getAlbumWithArtist(String albumName, String artistName){
        List<Album> albums = albumRepository.findByAlbumNameAndArtistName(albumName, artistName);
        if(albums == null || albums.isEmpty()){
            throw new EntityNotFoundException("Album Not Found with name: " + albumName + " and artist: " + artistName);
        }
        return albums;
    }

    public List<Album> getAlbumWithArtist(String artistName){
        List<Album> albums = albumRepository.findByArtistName(artistName);
        if(albums == null || albums.isEmpty()){
            throw new EntityNotFoundException("Album Not Found with artist: " + artistName);
        }
        return albums;
    }

    public Integer addAlbum(Album album){
        Integer id = null;
        album.setId(0);
        Album created = albumRepository.save(album);
        if (created != null) id = created.getId();
        return id;
    }

    public Boolean updateAlbum(Integer id, Album album){
        if(albumRepository.existsById(id)){
            album.setId(id);
            albumRepository.save(album);
        }
        else{
            throw new EntityNotFoundException("Album Not Found with id: " + id);
        }
        return true;
    }

    public Boolean deleteAlbum(Integer id){
        if(albumRepository.existsById(id)){
            Album album = albumRepository.findById(id).get();
            // Delete artists references
            List<Artist> artists = album.getArtists();
            artists.forEach(artist -> {
                artist.removeAlbum(album);
                artistRepository.save(artist);
            });

            albumRepository.delete(album);
        }
        else{
            throw new EntityNotFoundException("Album Not Found with id: " + id);
        }
        return true;
    }

    public Boolean deleteAlbums(){
        List<Album> albums = albumRepository.findAll();
        // Delete artists references
        albums.forEach(album -> {
            List<Artist> artists = album.getArtists();
            artists.forEach(artist -> {
                artist.removeAlbum(album);
                artistRepository.save(artist);
            });
        });

        albumRepository.deleteAll();
        return true;
    }
}

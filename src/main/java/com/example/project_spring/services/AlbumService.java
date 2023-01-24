package com.example.project_spring.services;

import com.example.project_spring.entity.Album;
import com.example.project_spring.entity.Artist;
import com.example.project_spring.repository.AlbumRepository;
import com.example.project_spring.repository.ArtistRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

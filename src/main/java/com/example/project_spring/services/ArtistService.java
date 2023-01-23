package com.example.project_spring.services;

import com.example.project_spring.entity.Artist;
import com.example.project_spring.repository.ArtistRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public List<Artist> getArtists(){
        return artistRepository.findAll();
    }

    public Artist getArtist(Integer id){
        return artistRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Artist Not Found with id: " + id));
    }

    public Artist getArtist(String name){
        return artistRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Artist Not Found with name: " + name));
    }

    public Boolean isAlbumFromArtist(String albumName, String artistName){
        List<Artist> artists = artistRepository.findByAlbumNameAndArtistName(albumName, artistName);
        return artists != null && !artists.isEmpty();
    }

    public Boolean addArtist(Artist artist){
        if(!artistRepository.existsByName(artist.getName())){
            artist.setId(0);
            artistRepository.save(artist);
        }
        else{
            throw new EntityExistsException("Artist already exists with name: " + artist.getName());
        }
        return true;
    }

    public Boolean updateArtist(Integer id, Artist artist){
        if(artistRepository.existsById(id)){
            if(!artistRepository.existsByName(artist.getName())){
                artist.setId(id);
                artistRepository.save(artist);
            }
            else{
                throw new EntityExistsException("Artist already exists with name: " + artist.getName());
            }
        }
        else{
            throw new EntityNotFoundException("Artist Not Found with id: " + id);
        }
        return true;
    }

    public Boolean deleteArtist(Integer id){
        if(artistRepository.existsById(id)){
            artistRepository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Artist Not Found with id: " + id);
        }
        return true;
    }
}

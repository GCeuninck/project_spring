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
        Boolean created = false;
        try{
            if(!artistRepository.existsByName(artist.getName())){
                artist.setId(0);
                artistRepository.save(artist);
                created = true;
            }
            else{
                throw new EntityExistsException("Artist already exists with name: " + artist.getName());
            }
        }
        catch (Exception exc){
            created = false;
        }
        return created;
    }

    public Boolean updateArtist(Artist artist){
        Boolean updated = false;
        try{
            if(artistRepository.existsById(artist.getId())){
                if(!artistRepository.existsByName(artist.getName())){
                    artistRepository.save(artist);
                    updated = true;
                }
                else{
                    throw new EntityExistsException("Artist already exists with name: " + artist.getName());
                }
            }
            else{
                throw new EntityNotFoundException("Artist Not Found with id: " + artist.getId());
            }
        }
        catch (Exception exc){
            updated = false;
        }
        return updated;
    }

    public Boolean deleteArtist(Integer id){
        Boolean deleted = false;
        try{
            if(artistRepository.existsById(id)){
                artistRepository.deleteById(id);
                deleted = true;
            }
            else{
                throw new EntityNotFoundException("Artist Not Found with id: " + id);
            }
        }
        catch (Exception exc){
            deleted = false;
        }
        return deleted;
    }
}

package com.example.project_spring.services.artist;

import com.example.project_spring.entity.Artist;
import com.example.project_spring.repository.ArtistRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService implements IArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    // region GetMethods

    public List<Artist> getArtists(){
        return artistRepository.findAll();
    }

    public Artist getArtist(Integer id){
        return artistRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Artist Not Found with id: " + id));
    }

    public Artist getArtist(String name){
        return artistRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Artist Not Found with name: " + name));
    }

    public List<Artist> getArtistWithAlbum(String albumName, String artistName){
        List<Artist> artists = artistRepository.findByAlbumNameAndArtistName(albumName, artistName);
        if(artists == null || artists.isEmpty()){
            throw new EntityNotFoundException("Artist Not Found with name: " + artistName + " and album: " + albumName);
        }
        return artists;
    }

    public List<Artist> getArtistWithAlbum(String albumName){
        List<Artist> artists = artistRepository.findByAlbumName(albumName);
        if(artists == null || artists.isEmpty()){
            throw new EntityNotFoundException("Artist Not Found with album: " + albumName);
        }
        return artists;
    }

    // endregion

    // region AddMethods

    public Integer addArtist(Artist artist){
        Integer id = null;
        if(!artistRepository.existsByName(artist.getName())){
            artist.setId(0);
            Artist created = artistRepository.save(artist);
            if (created != null) id = created.getId();
        }
        else{
            throw new EntityExistsException("Artist already exists with name: " + artist.getName());
        }
        return id;
    }

    // endregion

    // region UpdateMethods

    public Boolean updateArtist(Integer id, Artist artist){
        if(artistRepository.existsById(id)){
            Optional<Artist> found = artistRepository.findByName(artist.getName());
            if(found.isEmpty() || (!found.isEmpty() && found.get().getId().equals(id))){
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

    // endregion

    // region DeleteMethods

    public Boolean deleteArtist(Integer id){
        if(artistRepository.existsById(id)){
            artistRepository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Artist Not Found with id: " + id);
        }
        return true;
    }

    public Boolean deleteArtists(){
        artistRepository.deleteAll();
        return true;
    }

    // endregion
}

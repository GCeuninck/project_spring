package com.example.project_spring.services;

import com.example.project_spring.entity.Album;
import com.example.project_spring.repository.AlbumRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public List<Album> getAlbums(){
        return albumRepository.findAll();
    }

    public Album getAlbum(Integer id){
        return albumRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Album Not Found with id: " + id));
    }

    public Boolean addAlbum(Album album){
        Boolean created = false;
        try{
            albumRepository.save(album);
            created = true;
        }
        catch (Exception exc){
            created = false;
        }
        return created;
    }
}

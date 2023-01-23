package com.example.project_spring.controller;

import com.example.project_spring.entity.Album;
import com.example.project_spring.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project_spring/v1")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping(value = "/albums", produces = "application/json")
    public ResponseEntity<List<Album>> getAlbums(){
        List<Album> albums = albumService.getAlbums();
        return new ResponseEntity<>(albums, HttpStatus.OK) ;
    }

    @GetMapping(value = "/album/{id}", produces = "application/json")
    public ResponseEntity<Album> getAlbums(@PathVariable("id") Integer id)
    {
        Album album = albumService.getAlbum(id);
        return new ResponseEntity<>(album, HttpStatus.OK) ;
    }

    @PostMapping(value = "/album", consumes = "application/json", produces = "application/json")
    public ResponseEntity addAlbum(@RequestBody Album album)
    {
        Boolean created = albumService.addAlbum(album);
        if(created){
            return new ResponseEntity<>(HttpStatus.CREATED) ;
        }
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/album", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateAlbum(@RequestBody Album album)
    {
        Boolean updated = albumService.addAlbum(album);
        if(updated){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
        }
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }
}

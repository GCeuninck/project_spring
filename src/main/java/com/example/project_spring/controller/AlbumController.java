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
        Integer idCreated = albumService.addAlbum(album);
        if(idCreated != null){
            return new ResponseEntity<>("Album created with id: " + idCreated, HttpStatus.CREATED) ;
        }
        // Ne rentre pas dans le bloc en cas d'erreur, géré par ExceptionHandler
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/album/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateAlbum(@PathVariable("id") Integer id, @RequestBody Album album)
    {
        Boolean updated = albumService.updateAlbum(id, album);
        if(updated){
            return new ResponseEntity<>("Album updated with id: " + id, HttpStatus.OK) ;
        }
        // Ne rentre pas dans le bloc en cas d'erreur, géré par ExceptionHandler
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/album/{id}", produces = "application/json")
    public ResponseEntity deleteAlbum(@PathVariable("id") Integer id)
    {
        Boolean deleted = albumService.deleteAlbum(id);
        if(deleted){
            return new ResponseEntity<>("Album deleted with id: " + id, HttpStatus.OK) ;
        }
        // Ne rentre pas dans le bloc en cas d'erreur, géré par ExceptionHandler
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/albums", produces = "application/json")
    public ResponseEntity deleteAlbums()
    {
        Boolean deleted = albumService.deleteAlbums();
        if(deleted){
            return new ResponseEntity<>("Albums deleted", HttpStatus.OK) ;
        }
        // Ne rentre pas dans le bloc en cas d'erreur, géré par ExceptionHandler
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }
}

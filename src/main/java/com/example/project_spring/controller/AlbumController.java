package com.example.project_spring.controller;

import com.example.project_spring.entity.Album;
import com.example.project_spring.services.AlbumService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/project_spring/v1")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    // region GetMapping
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

    @GetMapping(value = "/album", produces = "application/json")
    public ResponseEntity getAlbums(@PathParam("name") String name)
    {
        List<Album> albums = albumService.getAlbums(name);
        return new ResponseEntity<>(albums, HttpStatus.OK) ;
    }

    @GetMapping(value = "/album/date/{date}", produces = "application/json")
    public ResponseEntity getAlbumsByDate(@PathVariable("date") Date date)
    {
        List<Album> albums = albumService.getAlbums(date);
        return new ResponseEntity<>(albums, HttpStatus.OK) ;
    }

    @GetMapping(value = "/album/start_date/{date}", produces = "application/json")
    public ResponseEntity getAlbumsByStartDate(@PathVariable("date") Date date)
    {
        List<Album> albums = albumService.getAlbumsByStartDate(date);
        return new ResponseEntity<>(albums, HttpStatus.OK) ;
    }

    @GetMapping(value = "/album/end_date/{date}", produces = "application/json")
    public ResponseEntity getAlbumsByEndDate(@PathVariable("date") Date date)
    {
        List<Album> albums = albumService.getAlbumsByEndDate(date);
        return new ResponseEntity<>(albums, HttpStatus.OK) ;
    }

    @GetMapping(value = "/album/date/{startDate}/{endDate}", produces = "application/json")
    public ResponseEntity getAlbumsByDate(@PathVariable("startDate") Date startDate, @PathVariable("endDate") Date endDate)
    {
        List<Album> albums = albumService.getAlbums(startDate, endDate);
        return new ResponseEntity<>(albums, HttpStatus.OK) ;
    }

    @GetMapping(value = "/album/{album}/artist/{artist}", produces = "application/json")
    public ResponseEntity getAlbum(@PathVariable("album") String album, @PathVariable("artist") String artist)
    {
        List<Album> albumWithArtist = albumService.getAlbumWithArtist(album, artist);
        return new ResponseEntity<>(albumWithArtist, HttpStatus.OK) ;
    }

    @GetMapping(value = "/album/artist/{artist}", produces = "application/json")
    public ResponseEntity getAlbumWithArtist(@PathVariable("artist") String artist)
    {
        List<Album> albumWithArtist = albumService.getAlbumWithArtist(artist);
        return new ResponseEntity<>(albumWithArtist, HttpStatus.OK) ;
    }

    // endregion

    // region PostMapping

    @PostMapping(value = "/album", consumes = "application/json", produces = "application/json")
    public ResponseEntity addAlbum(@Valid @RequestBody Album album)
    {
        Integer idCreated = albumService.addAlbum(album);
        if(idCreated != null){
            return new ResponseEntity<>(idCreated, HttpStatus.CREATED) ;
        }
        // Ne rentre pas dans le bloc en cas d'erreur, géré par ExceptionHandler
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    // endregion

    // region PutMapping
    @PutMapping(value = "/album/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateAlbum(@PathVariable("id") Integer id, @Valid @RequestBody Album album)
    {
        Boolean updated = albumService.updateAlbum(id, album);
        if(updated){
            return new ResponseEntity<>(id, HttpStatus.OK) ;
        }
        // Ne rentre pas dans le bloc en cas d'erreur, géré par ExceptionHandler
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    // endregion

    // region DeleteMapping
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

    // endregion
}

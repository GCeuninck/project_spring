package com.example.project_spring.controller;

import com.example.project_spring.entity.Artist;
import com.example.project_spring.services.artist.IArtistService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project_spring/v1")
public class ArtistController {

    @Autowired
    private IArtistService artistService;

    // region GetMapping

    @GetMapping(value = "/artists", produces = "application/json")
    public ResponseEntity<List<Artist>> getArtists(){
        List<Artist> artists = artistService.getArtists();
        return new ResponseEntity<>(artists, HttpStatus.OK) ;
    }

    @GetMapping(value = "/artist/{id}", produces = "application/json")
    public ResponseEntity<Artist> getArtist(@PathVariable("id") Integer id)
    {
        Artist artist = artistService.getArtist(id);
        return new ResponseEntity<>(artist, HttpStatus.OK) ;
    }

    @GetMapping(value = "/artist", produces = "application/json")
    public ResponseEntity<Artist> getArtist(@PathParam("name") String name)
    {
        Artist artist = artistService.getArtist(name);
        return new ResponseEntity<>(artist, HttpStatus.OK) ;
    }

    @GetMapping(value = "/artist/{artist}/album/{album}", produces = "application/json")
    public ResponseEntity getArtist(@PathVariable("artist") String artist, @PathVariable("album") String album)
    {
        List<Artist> artistWithAlbum = artistService.getArtistWithAlbum(album, artist);
        return new ResponseEntity<>(artistWithAlbum, HttpStatus.OK) ;
    }

    @GetMapping(value = "/artist/album/{album}", produces = "application/json")
    public ResponseEntity getArtistWithAlbum(@PathVariable("album") String album)
    {
        List<Artist> artistWithAlbum = artistService.getArtistWithAlbum(album);
        return new ResponseEntity<>(artistWithAlbum, HttpStatus.OK) ;
    }

    // endregion

    // region PostMapping
    @PostMapping(value = "/artist", consumes = "application/json", produces = "application/json")
    public ResponseEntity addArtist(@Valid @RequestBody Artist artist)
    {
        Integer idCreated = artistService.addArtist(artist);
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

    @PutMapping(value = "/artist/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateArtist(@PathVariable("id") Integer id, @Valid @RequestBody Artist artist)
    {
        Boolean updated = artistService.updateArtist(id, artist);
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

    @DeleteMapping(value = "/artist/{id}", produces = "application/json")
    public ResponseEntity deleteArtist(@PathVariable("id") Integer id)
    {
        Boolean deleted = artistService.deleteArtist(id);
        if(deleted){
            return new ResponseEntity<>("Artist deleted with id: " + id, HttpStatus.OK) ;
        }
        // Ne rentre pas dans le bloc en cas d'erreur, géré par ExceptionHandler
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/artists", produces = "application/json")
    public ResponseEntity deleteArtists()
    {
        Boolean deleted = artistService.deleteArtists();
        if(deleted){
            return new ResponseEntity<>("Artists deleted", HttpStatus.OK) ;
        }
        // Ne rentre pas dans le bloc en cas d'erreur, géré par ExceptionHandler
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    // endregion
}

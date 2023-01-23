package com.example.project_spring.controller;

import com.example.project_spring.entity.Artist;
import com.example.project_spring.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project_spring/v1")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

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

    @GetMapping(value = "/artist/{artist}/album/{album}", produces = "application/json")
    public ResponseEntity<Boolean> getArtist(@PathVariable("artist") String artist, @PathVariable("album") String album)
    {
        Boolean ok = artistService.isAlbumFromArtist(album, artist);
        return new ResponseEntity<>(ok, HttpStatus.OK) ;
    }

    @PostMapping(value = "/artist", consumes = "application/json", produces = "application/json")
    public ResponseEntity addArtist(@RequestBody Artist artist)
    {

        Boolean created = artistService.addArtist(artist);
        if(created){
            return new ResponseEntity<>(HttpStatus.CREATED) ;
        }
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/artist", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateArtist(@RequestBody Artist artist)
    {
        Boolean updated = artistService.updateArtist(artist);
        if(updated){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
        }
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/artist/{id}", produces = "application/json")
    public ResponseEntity deleteArtist(@PathVariable("id") Integer id)
    {
        Boolean deleted = artistService.deleteArtist(id);
        if(deleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
        }
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }
}

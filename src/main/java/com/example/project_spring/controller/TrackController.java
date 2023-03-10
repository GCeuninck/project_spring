package com.example.project_spring.controller;

import com.example.project_spring.entity.Track;
import com.example.project_spring.services.track.ITrackService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project_spring/v1")
public class TrackController {

    @Autowired
    private ITrackService trackService;

    // region GetMapping

    @GetMapping(value = "/tracks", produces = "application/json")
    public ResponseEntity<List<Track>> getTracks(){
        List<Track> tracks = trackService.getTracks();
        return new ResponseEntity<>(tracks, HttpStatus.OK) ;
    }

    @GetMapping(value = "/track", produces = "application/json")
    public ResponseEntity<List<Track>> getTracks(@PathParam("name") String name)
    {
        List<Track> tracks = trackService.getTracks(name);
        return new ResponseEntity<>(tracks, HttpStatus.OK) ;
    }

    @GetMapping(value = "/track/{id}", produces = "application/json")
    public ResponseEntity<Track> getTracks(@PathVariable("id") Integer id)
    {
        Track track = trackService.getTrack(id);
        return new ResponseEntity<>(track, HttpStatus.OK) ;
    }

    @GetMapping(value = "/tracks/album/{album}", produces = "application/json")
    public ResponseEntity<List<Track>> getTracksWithAlbum(@PathVariable("album") String album)
    {
        List<Track> tracksWithAlbum = trackService.getTracksWithAlbum(album);
        return new ResponseEntity<>(tracksWithAlbum, HttpStatus.OK) ;
    }

    @GetMapping(value = "/tracks/artist/{artist}", produces = "application/json")
    public ResponseEntity<List<Track>> getTracksWithArtist(@PathVariable("artist") String artist)
    {
        List<Track> tracksWithArtist = trackService.getTracksWithArtist(artist);
        return new ResponseEntity<>(tracksWithArtist, HttpStatus.OK) ;
    }

    // endregion

    // region PostMapping

    @PostMapping(value = "/track", consumes = "application/json", produces = "application/json")
    public ResponseEntity addTrack(@Valid @RequestBody Track track)
    {
        Integer idCreated = trackService.addTrack(track);
        if(idCreated != null){
            return new ResponseEntity<>(idCreated, HttpStatus.CREATED) ;
        }
        // Ne rentre pas dans le bloc en cas d'erreur, g??r?? par ExceptionHandler
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    // endregion

    // region PutMapping

    @PutMapping(value = "/track/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateTrack(@PathVariable("id") Integer id, @Valid @RequestBody Track track)
    {
        Boolean updated = trackService.updateTrack(id, track);
        if(updated){
            return new ResponseEntity<>(id, HttpStatus.OK) ;
        }
        // Ne rentre pas dans le bloc en cas d'erreur, g??r?? par ExceptionHandler
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    // endregion

    // region DeleteMapping

    @DeleteMapping(value = "/track/{id}", produces = "application/json")
    public ResponseEntity<String> deleteTrack(@PathVariable("id") Integer id)
    {
        Boolean deleted = trackService.deleteTrack(id);
        if(deleted){
            return new ResponseEntity<>("Track deleted with id: " + id, HttpStatus.OK) ;
        }
        // Ne rentre pas dans le bloc en cas d'erreur, g??r?? par ExceptionHandler
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/tracks", produces = "application/json")
    public ResponseEntity<String> deleteTracks()
    {
        Boolean deleted = trackService.deleteTracks();
        if(deleted){
            return new ResponseEntity<>("Tracks deleted", HttpStatus.OK) ;
        }
        // Ne rentre pas dans le bloc en cas d'erreur, g??r?? par ExceptionHandler
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    // endregion
}

package com.example.project_spring.controller;

import com.example.project_spring.entity.Track;
import com.example.project_spring.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project_spring/v1")
public class TrackController {

    @Autowired
    private TrackService trackService;

    @GetMapping(value = "/tracks", produces = "application/json")
    public ResponseEntity<List<Track>> getTracks(){
        List<Track> tracks = trackService.getTracks();
        return new ResponseEntity<>(tracks, HttpStatus.OK) ;
    }

    @GetMapping(value = "/track/{id}", produces = "application/json")
    public ResponseEntity<Track> getTracks(@PathVariable("id") Integer id)
    {
        Track track = trackService.getTrack(id);
        return new ResponseEntity<>(track, HttpStatus.OK) ;
    }

    @PostMapping(value = "/track", consumes = "application/json", produces = "application/json")
    public ResponseEntity addTrack(@RequestBody Track track)
    {
        Integer idCreated = trackService.addTrack(track);
        if(idCreated != null){
            return new ResponseEntity<>("Track created with id: " + idCreated, HttpStatus.CREATED) ;
        }
        // Ne rentre pas dans le bloc en cas d'erreur, géré par ExceptionHandler
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/track/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity updateTrack(@PathVariable("id") Integer id, @RequestBody Track track)
    {
        Boolean updated = trackService.updateTrack(id, track);
        if(updated){
            return new ResponseEntity<>("Track updated with id: " + id, HttpStatus.OK) ;
        }
        // Ne rentre pas dans le bloc en cas d'erreur, géré par ExceptionHandler
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/track/{id}", produces = "application/json")
    public ResponseEntity deleteTrack(@PathVariable("id") Integer id)
    {
        Boolean deleted = trackService.deleteTrack(id);
        if(deleted){
            return new ResponseEntity<>("Track deleted with id: " + id, HttpStatus.OK) ;
        }
        // Ne rentre pas dans le bloc en cas d'erreur, géré par ExceptionHandler
        else{
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }
    }

}

package com.example.project_spring.services;

import com.example.project_spring.entity.Track;
import com.example.project_spring.repository.TrackRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {

    @Autowired
    private TrackRepository trackRepository;

    public List<Track> getTracks(){
        return trackRepository.findAll();
    }

    public Track getTrack(Integer id){
        return trackRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Track Not Found with id: " + id));
    }

    public Track getTrack(String name){
        return trackRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Track Not Found with name: " + name));
    }

    public Integer addTrack(Track track){
        Integer id = null;
        track.setId(0);
        Track created = trackRepository.save(track);
        if (created != null) id = created.getId();
        return id;
    }

    public Boolean updateTrack(Integer id, Track track){
        if(trackRepository.existsById(id)){
            track.setId(id);
            trackRepository.save(track);
        }
        else{
            throw new EntityNotFoundException("Track Not Found with id: " + id);
        }
        return true;
    }

    public Boolean deleteTrack(Integer id){
        if(trackRepository.existsById(id)){
            trackRepository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Track Not Found with id: " + id);
        }
        return true;
    }

    public Boolean deleteTracks(){
        trackRepository.deleteAll();
        return true;
    }
}

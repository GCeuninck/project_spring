package com.example.project_spring.services.track;

import com.example.project_spring.entity.Track;
import com.example.project_spring.repository.TrackRepository;
import com.example.project_spring.services.track.ITrackService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService implements ITrackService {

    @Autowired
    private TrackRepository trackRepository;

    // region GetMethods

    public List<Track> getTracks(){
        return trackRepository.findAll();
    }

    public Track getTrack(Integer id){
        return trackRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Track Not Found with id: " + id));
    }

    public List<Track> getTracks(String name){
        List<Track> tracks = trackRepository.findAllByName(name);
        if(tracks == null || tracks.isEmpty()){
            throw new EntityNotFoundException("Track Not Found with name: " + name);
        }
        return tracks;
    }

    public List<Track> getTracksWithAlbum(String albumName){
        List<Track> tracks = trackRepository.findByAlbumName(albumName);
        if(tracks == null || tracks.isEmpty()){
            throw new EntityNotFoundException("Track Not Found with album: " + albumName);
        }
        return tracks;
    }

    public List<Track> getTracksWithArtist(String artistName){
        List<Track> tracks = trackRepository.findByArtistName(artistName);
        if(tracks == null || tracks.isEmpty()){
            throw new EntityNotFoundException("Track Not Found with artist: " + artistName);
        }
        return tracks;
    }

    // endregion

    // region AddMethods

    public Integer addTrack(Track track){
        Integer id = null;
        track.setId(0);
        Track created = trackRepository.save(track);
        if (created != null) id = created.getId();
        return id;
    }

    // endregion

    // region UpdateMethods

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

    // endregion

    // region DeleteMethods

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

    // endregion
}

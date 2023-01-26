package com.example.project_spring.services.track;

import com.example.project_spring.entity.Track;

import java.util.List;

public interface ITrackService {
    List<Track> getTracks();
    Track getTrack(Integer id);
    List<Track> getTracks(String name);
    List<Track> getTracksWithAlbum(String albumName);
    List<Track> getTracksWithArtist(String artistName);
    Integer addTrack(Track track);
    Boolean updateTrack(Integer id, Track track);
    Boolean deleteTrack(Integer id);
    Boolean deleteTracks();
}

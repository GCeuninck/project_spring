package com.example.project_spring.controller;

import com.example.project_spring.ProjectSpringApplicationTests;
import com.example.project_spring.entity.Album;
import com.example.project_spring.entity.Track;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class AlbumControllerTest extends ProjectSpringApplicationTests {

    @Test

    public void testFindAlbum() throws Exception {

        // Create Tracks
        Track track1 = new Track();
        track1.setName("Track 1");
        track1.setDuration(1);
        track1.setTrackNumber(1);

        Track track2 = new Track();
        track2.setName("Track 2");
        track2.setDuration(2);
        track2.setTrackNumber(2);

        String idTrack1 = mockMvc.perform(
                        post("/project_spring/v1/track")
                                .content(asJsonString(track1)).contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        if(idTrack1.isEmpty()){
            throw new RuntimeException("Track not created for the test: Find Album");
        }
        else{
            track1.setId(Integer.parseInt(idTrack1));
        }


        String idTrack2 = mockMvc.perform(
                        post("/project_spring/v1/track")
                                .content(asJsonString(track2)).contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        if(idTrack2.isEmpty()){
            throw new RuntimeException("Track not created for the test: Find Album");
        }
        else{
            track2.setId(Integer.parseInt(idTrack2));
        }

        // Create Album

        Album album = new Album();
        album.setName("Album 1");
        album.setType("album");
        long millis = System.currentTimeMillis();
        album.setReleaseDate(new java.sql.Date(millis));

        List<Track> tracks = new ArrayList<>();
        tracks.add(track1);
        tracks.add(track2);
        album.setTracks(tracks);
        album.setTotalTracks(tracks.size());

        String id = mockMvc.perform(
                        post("/project_spring/v1/album")
                                .content(asJsonString(album)).contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        String response = mockMvc.perform(get("/project_spring/v1/album/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(album.getName()))
                .andExpect(jsonPath("$.type").value(album.getType()))
                .andExpect(jsonPath("$.releaseDate").value(album.getReleaseDate().toString()))
                .andExpect(jsonPath("$.totalTracks").value(album.getTotalTracks()))
                .andExpect(jsonPath("$.tracks").isNotEmpty())
                .andReturn().getResponse().getContentAsString();
    }


}

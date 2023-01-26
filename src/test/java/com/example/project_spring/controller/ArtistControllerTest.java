package com.example.project_spring.controller;

import com.example.project_spring.ProjectSpringApplicationTests;
import com.example.project_spring.entity.Album;
import com.example.project_spring.entity.Artist;
import com.example.project_spring.entity.Genre;
import com.example.project_spring.entity.Track;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class ArtistControllerTest extends ProjectSpringApplicationTests {

    @Test
    public void testFindArtist() throws Exception {

        // region Tracks Creation

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

        // endregion

        // region Albums Creation

        Album album1 = new Album();
        album1.setName("Album 1");
        album1.setType("album");
        long millis = System.currentTimeMillis();
        album1.setReleaseDate(new java.sql.Date(millis));

        List<Track> tracks1 = new ArrayList<>();
        tracks1.add(track1);
        album1.setTracks(tracks1);
        album1.setTotalTracks(tracks1.size());

        Album album2 = new Album();
        album2.setName("Album 2");
        album2.setType("album");
        album2.setReleaseDate(new java.sql.Date(millis));

        List<Track> tracks2 = new ArrayList<>();
        tracks2.add(track2);
        album2.setTracks(tracks2);
        album2.setTotalTracks(tracks2.size());


        String idAlbum1 = mockMvc.perform(
                        post("/project_spring/v1/album")
                                .content(asJsonString(album1)).contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        if(idAlbum1.isEmpty()){
            throw new RuntimeException("Album not created for the test: Find Artist");
        }
        else{
            album1.setId(Integer.parseInt(idAlbum1));
        }

        String idAlbum2 = mockMvc.perform(
                        post("/project_spring/v1/album")
                                .content(asJsonString(album2)).contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        if(idAlbum2.isEmpty()){
            throw new RuntimeException("Album not created for the test: Find Artist");
        }
        else{
            album2.setId(Integer.parseInt(idAlbum2));
        }

        // endregion

        // region Genres Creation

        List<Genre> genres = new ArrayList<>();

        // endregion

        // region Album Creation

        List<Album> albums = new ArrayList<>();
        albums.add(album1);
        albums.add(album2);

        Artist artist = new Artist();
        artist.setName("Get Artist");
        artist.setGenres(genres);
        artist.setAlbums(albums);

        String id = mockMvc.perform(
                        post("/project_spring/v1/artist")
                                .content(asJsonString(artist)).contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        // endregion

        String response = mockMvc.perform(get("/project_spring/v1/artist/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(artist.getName()))
                .andExpect(jsonPath("$.albums").isNotEmpty())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testUpdateArtist() throws Exception {

        // region Tracks Creation

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

        // endregion

        // region Albums Creation

        Album album1 = new Album();
        album1.setName("Album 1");
        album1.setType("album");
        long millis = System.currentTimeMillis();
        album1.setReleaseDate(new java.sql.Date(millis));

        List<Track> tracks1 = new ArrayList<>();
        tracks1.add(track1);
        album1.setTracks(tracks1);
        album1.setTotalTracks(tracks1.size());

        Album album2 = new Album();
        album2.setName("Album 2");
        album2.setType("album");
        album2.setReleaseDate(new java.sql.Date(millis));

        List<Track> tracks2 = new ArrayList<>();
        tracks2.add(track2);
        album2.setTracks(tracks2);
        album2.setTotalTracks(tracks2.size());


        String idAlbum1 = mockMvc.perform(
                        post("/project_spring/v1/album")
                                .content(asJsonString(album1)).contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        if(idAlbum1.isEmpty()){
            throw new RuntimeException("Album not created for the test: Find Artist");
        }
        else{
            album1.setId(Integer.parseInt(idAlbum1));
        }

        String idAlbum2 = mockMvc.perform(
                        post("/project_spring/v1/album")
                                .content(asJsonString(album2)).contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        if(idAlbum2.isEmpty()){
            throw new RuntimeException("Album not created for the test: Find Artist");
        }
        else{
            album2.setId(Integer.parseInt(idAlbum2));
        }

        // endregion

        // region Genres Creation

        List<Genre> genres = new ArrayList<>();

        // endregion

        // region Album Creation

        List<Album> albums = new ArrayList<>();
        albums.add(album1);
        albums.add(album2);

        Artist artist = new Artist();
        artist.setName("Update Artist");
        artist.setGenres(genres);
        artist.setAlbums(albums);

        String id = mockMvc.perform(
                        post("/project_spring/v1/artist")
                                .content(asJsonString(artist)).contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        mockMvc.perform(get("/project_spring/v1/artist/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(artist.getName()))
                .andExpect(jsonPath("$.albums").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        // endregion

        if(!id.isEmpty()){
            albums.remove(album1);

            Artist artistModif = new Artist();
            artistModif.setId(Integer.parseInt(id));
            artistModif.setName("Update Artist Modif");
            artistModif.setGenres(genres);
            artistModif.setAlbums(albums);

            String updateResponse = mockMvc.perform(
                            put("/project_spring/v1/artist/" + id)
                                    .content(asJsonString(artistModif)).contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$").isNotEmpty())
                    .andReturn().getResponse().getContentAsString();

            String response = mockMvc.perform(get("/project_spring/v1/artist/" + id))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.id").value(id))
                    .andExpect(jsonPath("$.name").value(artistModif.getName()))
                    .andExpect(jsonPath("$.albums").isNotEmpty())
                    .andReturn().getResponse().getContentAsString();
        }
        else{
            throw new RuntimeException("Artist not created for the test: Update Artist");
        }
    }

    @Test
    public void testDeleteArtist() throws Exception {

        // region Tracks Creation

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

        // endregion

        // region Albums Creation

        Album album1 = new Album();
        album1.setName("Album 1");
        album1.setType("album");
        long millis = System.currentTimeMillis();
        album1.setReleaseDate(new java.sql.Date(millis));

        List<Track> tracks1 = new ArrayList<>();
        tracks1.add(track1);
        album1.setTracks(tracks1);
        album1.setTotalTracks(tracks1.size());

        Album album2 = new Album();
        album2.setName("Album 2");
        album2.setType("album");
        album2.setReleaseDate(new java.sql.Date(millis));

        List<Track> tracks2 = new ArrayList<>();
        tracks2.add(track2);
        album2.setTracks(tracks2);
        album2.setTotalTracks(tracks2.size());


        String idAlbum1 = mockMvc.perform(
                        post("/project_spring/v1/album")
                                .content(asJsonString(album1)).contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        if(idAlbum1.isEmpty()){
            throw new RuntimeException("Album not created for the test: Find Artist");
        }
        else{
            album1.setId(Integer.parseInt(idAlbum1));
        }

        String idAlbum2 = mockMvc.perform(
                        post("/project_spring/v1/album")
                                .content(asJsonString(album2)).contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        if(idAlbum2.isEmpty()){
            throw new RuntimeException("Album not created for the test: Find Artist");
        }
        else{
            album2.setId(Integer.parseInt(idAlbum2));
        }

        // endregion

        // region Genres Creation

        List<Genre> genres = new ArrayList<>();

        // endregion

        // region Album Creation

        List<Album> albums = new ArrayList<>();
        albums.add(album1);
        albums.add(album2);

        Artist artist = new Artist();
        artist.setName("Delete Artist");
        artist.setGenres(genres);
        artist.setAlbums(albums);

        String id = mockMvc.perform(
                        post("/project_spring/v1/artist")
                                .content(asJsonString(artist)).contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        String response = mockMvc.perform(get("/project_spring/v1/artist/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(artist.getName()))
                .andExpect(jsonPath("$.albums").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        // endregion

        String deleteResponse = mockMvc.perform(delete("/project_spring/v1/artist/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").value("Artist deleted with id: " + id))
                .andReturn().getResponse().getContentAsString();

        String errorGetResponse = mockMvc.perform(get("/project_spring/v1/artist/" + id))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("Artist Not Found with id: " + id))
                .andReturn().getResponse().getContentAsString();

        // region Delete All

        String deleteAllResponse = mockMvc.perform(delete("/project_spring/v1/artists"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").value("Artists deleted"))
                .andReturn().getResponse().getContentAsString();

        // endregion
    }
}

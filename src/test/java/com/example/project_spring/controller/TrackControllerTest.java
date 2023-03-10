package com.example.project_spring.controller;

import com.example.project_spring.ProjectSpringApplicationTests;
import com.example.project_spring.entity.Track;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TrackControllerTest extends ProjectSpringApplicationTests {

    @Test
    public void testFindTrack() throws Exception {

        // region Track Creation

        Track track = new Track();
        track.setName("Track Get Track");
        track.setDuration(2);
        track.setTrackNumber(1);

        String id = mockMvc.perform(
                        post("/project_spring/v1/track")
                                .content(asJsonString(track)).contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        // endregion

        String response = mockMvc.perform(get("/project_spring/v1/track/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(track.getName()))
                .andExpect(jsonPath("$.duration").value(track.getDuration()))
                .andExpect(jsonPath("$.trackNumber").value(track.getTrackNumber()))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testCreateTrack() throws Exception {

        Track track = new Track();
        track.setName("Track Creation");
        track.setDuration(2);
        track.setTrackNumber(1);

        String id = mockMvc.perform(
                    post("/project_spring/v1/track")
                    .content(asJsonString(track)).contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        String response = mockMvc.perform(get("/project_spring/v1/track/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(track.getName()))
                .andExpect(jsonPath("$.duration").value(track.getDuration()))
                .andExpect(jsonPath("$.trackNumber").value(track.getTrackNumber()))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testUpdateTrack() throws Exception {

        // region Track Creation

        Track track = new Track();
        track.setName("Track Update Track");
        track.setDuration(1);
        track.setTrackNumber(1);

        String id = mockMvc.perform(
                        post("/project_spring/v1/track")
                                .content(asJsonString(track)).contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        String createResponse = mockMvc.perform(get("/project_spring/v1/track/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(track.getName()))
                .andExpect(jsonPath("$.duration").value(track.getDuration()))
                .andExpect(jsonPath("$.trackNumber").value(track.getTrackNumber()))
                .andReturn().getResponse().getContentAsString();

        // endregion

        if(!id.isEmpty()){
            Track trackModif = new Track();
            trackModif.setId(Integer.parseInt(id));
            trackModif.setName("Track Update Track Modif");
            trackModif.setDuration(2);
            trackModif.setTrackNumber(2);

            String updateResponse = mockMvc.perform(
                            put("/project_spring/v1/track/" + id)
                                    .content(asJsonString(trackModif)).contentType("application/json"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$").isNotEmpty())
                    .andReturn().getResponse().getContentAsString();

            String response = mockMvc.perform(get("/project_spring/v1/track/" + id))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.id").value(id))
                    .andExpect(jsonPath("$.name").value(trackModif.getName()))
                    .andExpect(jsonPath("$.duration").value(trackModif.getDuration()))
                    .andExpect(jsonPath("$.trackNumber").value(trackModif.getTrackNumber()))
                    .andReturn().getResponse().getContentAsString();
        }
        else{
            throw new RuntimeException("Track not created for the test: Update Track");
        }
    }

    @Test
    public void testDeleteTrack() throws Exception {

        // region Track Creation

        Track track = new Track();
        track.setName("Track Delete");
        track.setDuration(2);
        track.setTrackNumber(1);

        String id = mockMvc.perform(
                        post("/project_spring/v1/track")
                                .content(asJsonString(track)).contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andReturn().getResponse().getContentAsString();

        String response = mockMvc.perform(get("/project_spring/v1/track/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(track.getName()))
                .andExpect(jsonPath("$.duration").value(track.getDuration()))
                .andExpect(jsonPath("$.trackNumber").value(track.getTrackNumber()))
                .andReturn().getResponse().getContentAsString();

        // endregion

        String deleteResponse = mockMvc.perform(delete("/project_spring/v1/track/" + id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").value("Track deleted with id: " + id))
                .andReturn().getResponse().getContentAsString();

        String errorGetResponse = mockMvc.perform(get("/project_spring/v1/track/" + id))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("Track Not Found with id: " + id))
                .andReturn().getResponse().getContentAsString();

        // region Delete All

        String deleteAllResponse = mockMvc.perform(delete("/project_spring/v1/tracks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").value("Tracks deleted"))
                .andReturn().getResponse().getContentAsString();

        // endregion
    }
}

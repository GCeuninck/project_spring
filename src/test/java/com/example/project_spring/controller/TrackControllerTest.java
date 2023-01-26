package com.example.project_spring.controller;

import com.example.project_spring.ProjectSpringApplicationTests;
import com.example.project_spring.entity.Track;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TrackControllerTest extends ProjectSpringApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testFindTrack() throws Exception {

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
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package com.example.project_spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Type is mandatory")
    private String type;

    @Column(name = "release_date", nullable = false)
    @NotNull(message = "Release Date is mandatory")
    private Date releaseDate;

    @Column(name = "total_tracks", nullable = false)
    @NotNull(message = "Total Tracks is mandatory")
    private Integer totalTracks;

    @ManyToMany(mappedBy = "albums")
    @JsonIgnore
    private List<Artist> artists;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "album_id")
    private List<Track> tracks;

    // region Getter/Setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getTotalTracks() {
        return totalTracks;
    }

    public void setTotalTracks(Integer totalTracks) {
        this.totalTracks = totalTracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    // endregion

    // region Methods

    public void add(Track track){
        if(tracks == null){
            tracks = new ArrayList<>();
        }

        tracks.add(track);
    }

    public void addArtist(Artist artist){
        if(artists == null){
            artists = new ArrayList<>();
        }

        artists.add(artist);
        artist.getAlbums().add(this);
    }

    public void removeArtist(Artist artist){
        if(artists == null){
            artists = new ArrayList<>();
        }

        artists.remove(artist);
    }

    // endregion
}

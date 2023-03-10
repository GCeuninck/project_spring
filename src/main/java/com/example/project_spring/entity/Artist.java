package com.example.project_spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "artist_genre", joinColumns = @JoinColumn(name = "artist_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH }, fetch = FetchType.EAGER)
    @JoinTable(name = "artist_album", joinColumns = @JoinColumn(name = "artist_id"), inverseJoinColumns = @JoinColumn(name = "album_id"))
    private List<Album> albums;

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

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    // endregion

    // region Methods

    public void add(Album album){
        if(albums == null){
            albums = new ArrayList<>();
        }

        albums.add(album);
        album.getArtists().add(this);
    }

    public void removeAlbum(Album album){
        if(albums == null){
            albums = new ArrayList<>();
        }

        albums.remove(album);
    }

    // endregion
}

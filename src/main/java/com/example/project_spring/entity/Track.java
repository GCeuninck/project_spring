package com.example.project_spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "track")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "duration_ms", nullable = false)
    @NotNull(message = "Duration(ms) is mandatory")
    private Integer duration;

    @Column(name = "track_number", nullable = false)
    @NotBlank(message = "Track Number is mandatory")
    private Integer trackNumber;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "album_id")
    private Album album;

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }

    // endregion
}

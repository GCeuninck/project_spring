package com.example.project_spring.repository;

import com.example.project_spring.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackRepository  extends JpaRepository<Track, Integer> {

    Optional<Track> findById(Integer integer);
    Optional<Track> findByName(String name);
    List<Track> findAll();
}

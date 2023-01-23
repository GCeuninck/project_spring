package com.example.project_spring.repository;

import com.example.project_spring.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

    Optional<Album> findById(Integer integer);
    List<Album> findAll();
}

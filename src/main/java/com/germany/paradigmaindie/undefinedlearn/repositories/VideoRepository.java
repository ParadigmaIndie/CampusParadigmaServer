package com.germany.paradigmaindie.undefinedlearn.repositories;

import com.germany.paradigmaindie.undefinedlearn.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video,Long> {
    Optional<Video> findByName(String name);
}
